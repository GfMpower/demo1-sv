<template>
  <div style="padding: 20px">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>订单管理</span>
        </div>
      </template>

      <el-form :model="searchForm" label-width="80px" inline>
        <el-form-item label="订单编号" prop="orderNo">
          <el-input v-model="searchForm.orderNo" placeholder="请输入订单编号"/>
        </el-form-item>
        <el-form-item label="订单状态" prop="status">
          <el-select style="width: 200px;" v-model="searchForm.status" placeholder="请选择订单状态">
            <el-option
                v-for="item in statusOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">搜索</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table :data="orderList" row-key="id" style="width: 100%;"
                :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
      >
        <el-table-column prop="orderNo" label="订单编号">
          <template #default="scope">
            <el-link @click="handleDetail(scope.row)" type="primary">{{ scope.row.orderNo }}</el-link>
          </template>
        </el-table-column>
        <el-table-column prop="nickname" label="下单用户昵称"></el-table-column>
        <el-table-column prop="totalAmount" label="总金额">
          <template #default="scope">
            {{ scope.row.totalAmount + '元' }}
          </template>
        </el-table-column>
        <el-table-column prop="status" label="订单状态">
          <template #default="scope">
            {{ getStatusInfo(scope.row.status) }}
          </template>
        </el-table-column>
        <el-table-column prop="receiverName" label="收货人"></el-table-column>
        <el-table-column prop="receiverPhone" label="联系电话"></el-table-column>
      </el-table>

      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageInfo.currentPage"
          :page-sizes="[10,20,30,50]"
          :page-size="pageInfo.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pageInfo.total"
      />
    </el-card>

    <!-- 订单详情对话框 -->
    <el-dialog
        title="订单详情"
        v-model="open"
        width="800px"
    >
      <el-descriptions :column="2" border>
        <el-descriptions-item label="订单编号">{{ orderDetailInfo.orderNo }}</el-descriptions-item>
        <el-descriptions-item label="用户昵称">{{ orderDetailInfo.nickname }}</el-descriptions-item>
        <el-descriptions-item label="总金额">{{ orderDetailInfo.totalAmount + '元' }}</el-descriptions-item>
        <el-descriptions-item label="订单状态">{{ getStatusInfo(orderDetailInfo.status) }}</el-descriptions-item>
        <el-descriptions-item label="收货人">{{ orderDetailInfo.receiverName }}</el-descriptions-item>
        <el-descriptions-item label="联系电话">{{ orderDetailInfo.receiverPhone }}</el-descriptions-item>
      </el-descriptions>

      <el-table :data="orderDetailInfo.orderItems" style="margin-top: 20px" border>
        <el-table-column prop="productName" label="商品名称"></el-table-column>
        <el-table-column prop="price" label="单价"></el-table-column>
        <el-table-column prop="quantity" label="数量"></el-table-column>
        <el-table-column prop="subtotal" label="小计"></el-table-column>
      </el-table>

      <template #footer>
                <span>
                    <el-button @click="open = false">关闭</el-button>
                </span>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import orderApi from '@/api/order.js'

//是否打开弹窗
const open = ref(false)

//订单详情信息
const orderDetailInfo = ref({})

//查看订单详情信息
const handleDetail = (row) => {
  orderApi.getOrderById(row.id).then(res => {
    orderDetailInfo.value = res.data
    open.value = true
  })
}

//订单状态选项
const statusOptions = [
  {value: '0', label: '已取消'},
  {value: 1, label: '待支付'},
  {value: 2, label: '已支付'},
  {value: 3, label: '已发货'},
  {value: 4, label: '已完成'}
]

//获取状态标签
const getStatusInfo = (status) => {
  switch (status) {
    case 0:
      return '已取消'
    case 1:
      return '待支付'
    case 2:
      return '已支付'
    case 3:
      return '已发货'
    case 4:
      return '已完成'
  }
}

//分页相关
const handleSizeChange = (val) => {
  pageInfo.pageSize = val
  getOrderList()
}

const handleCurrentChange = (val) => {
  pageInfo.currentPage = val
  getOrderList()
}

//重置
const handleReset = () => {
  searchForm.orderNo = ''
  searchForm.status = ''
  pageInfo.currentPage = 1
  getOrderList()
}

//搜索
const handleSearch = () => {
  pageInfo.currentPage = 1
  getOrderList()
}

//条件查询参数
const searchForm = reactive({
  orderNo: '',
  status: ''
})

//表格数据
const orderList = ref([])
const categoryList = ref([])

//查询参数
const pageInfo = reactive({
  currentPage: 1,
  pageSize: 10,
  total: 0
})

//获取商品列表
const getOrderList = () => {
  const params = {
    pageNum: pageInfo.currentPage,
    pageSize: pageInfo.pageSize,
    orderNo: searchForm.orderNo || null,
    status: searchForm.status || null,
  }
  orderApi.getOrderList(params).then(res => {
    orderList.value = res.data.list
    pageInfo.total = res.data.total
  })
}

//组件挂载时调用
onMounted(() => {
  getOrderList()
})
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>