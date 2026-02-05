<template>
  <div style="padding: 20px">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>商品管理</span>
          <el-button type="primary" @click="handleCreate">新增商品</el-button>
        </div>
      </template>

      <el-form :model="searchForm" label-width="80px" inline>
        <el-form-item label="商品名称" prop="name">
          <el-input v-model="searchForm.name" placeholder="请输入商品名称"/>
        </el-form-item>
        <el-form-item label="分类" prop="categoryId">
          <el-select style="width: 200px;" v-model="searchForm.categoryId" placeholder="请选择分类">
            <el-option
                v-for="category in categoryList"
                :key="category.id"
                :label="category.name"
                :value="category.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">搜索</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table :data="productList" row-key="id" style="width: 100%;"
                :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
      >
        <el-table-column prop="name" label="商品名称"></el-table-column>
        <el-table-column prop="categoryName" label="分类"></el-table-column>
        <el-table-column prop="price" label="价格"></el-table-column>
        <el-table-column prop="stock" label="库存"></el-table-column>
        <el-table-column prop="status" label="状态">
          <template #default="scope">
            <el-tag>
              {{ scope.row.status === 1 ? '上架' : '下架' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button @click="handleEdit(scope.row)">编辑</el-button>
            <el-button @click="handleDelete(scope.row)" type="danger">删除</el-button>
          </template>
        </el-table-column>
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

    <!-- 新增或者编辑对话框 -->
    <el-dialog
        :title="dialogTitle"
        v-model="open"
        width="500px"
        @close=""
    >
      <el-form
          ref="productFormRef"
          :model="productForm"
          :rules="rules"
          label-width="100px"
      >
        <el-form-item label="商品名称" prop="name">
          <el-input v-model="productForm.name" placeholder="请输入商品名称"/>
        </el-form-item>

        <el-form-item label="分类" prop="categoryId">
          <el-select v-model="productForm.categoryId" placeholder="请选择分类">
            <el-option
                v-for="category in categoryList"
                :key="category.id"
                :label="category.name"
                :value="category.id"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="价格" prop="price">
          <el-input-number v-model="productForm.price" placeholder="请输入价格" style="width: 100%;"/>
        </el-form-item>

        <el-form-item label="库存" prop="stock">
          <el-input-number v-model="productForm.stock" placeholder="请输入库存" style="width: 100%;"/>
        </el-form-item>

        <el-form-item label="描述" prop="description">
          <el-input type="textarea" v-model="productForm.description" placeholder="请输入描述"
                    style="width: 100%;"/>
        </el-form-item>

        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="productForm.status">
            <el-radio :value=1>上架</el-radio>
            <el-radio :value=0>下架</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>

      <template #footer>
                <span style="text-align: right">
                    <el-button @click="open = false">取消</el-button>
                    <el-button type="primary" @click="submitForm">确定</el-button>
                </span>
      </template>

    </el-dialog>

  </div>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import productApi from '@/api/product.js'
import categoryApi from '@/api/category.js'
import {ElMessage, ElMessageBox} from "element-plus";

//分页相关
const handleSizeChange = (val) => {
  pageInfo.pageSize = val
  getProductList()
}

const handleCurrentChange = (val) => {
  pageInfo.currentPage = val
  getProductList()
}

//重置
const handleReset = () => {
  searchForm.name = ''
  searchForm.categoryId = ''
  pageInfo.currentPage = 1
  getProductList()
}

//搜索
const handleSearch = () => {
  pageInfo.currentPage = 1
  getProductList()
}

//分类表单参数
let productForm = reactive({
  id: '',
  name: '',
  categoryId: '',
  price: 0,
  stock: 0,
  description: '',
  status: 1,
})

//条件查询参数
const searchForm = reactive({
  name: '',
  categoryId: ''
})

//表单验证规则
const rules = reactive({
  name: [
    {required: true, message: '请输入商品名称', trigger: 'blur'}
  ],
  categoryId: [
    {required: true, message: '请选择分类', trigger: 'change'}
  ],
  price: [
    {required: true, message: '请输入价格', trigger: 'blur'}
  ]
})

//对话框title
const dialogTitle = ref('')

//对话框是否打开
const open = ref(false)

//表格数据
const productList = ref([])
const categoryList = ref([])

//是否编辑状态
const isEdit = ref(false)

const productFormRef = ref()

//编辑商品
const handleEdit = (row) => {
  isEdit.value = true
  productForm = row
  open.value = true
}

//删除分类
const handleDelete = (row) => {
  ElMessageBox.confirm(
      '确定要删除该条商品信息吗?',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  )
      .then(() => {
        productApi.deleteProduct(row.id).then(res => {
          ElMessage.success('删除成功')
          getProductList()
        })
      })
      .catch(() => {
        ElMessage({type: 'info', message: '取消删除',})
      })
}

//提交表单
const submitForm = () => {
  console.log(isEdit, '看看当前是新增还是修改')
  productFormRef.value.validate((valid, fields) => {
    if (valid) {
      if (isEdit.value) {
        //编辑
        productApi.updateProduct(productForm).then(res => {
          ElMessage.success('修改成功')
          open.value = false
          getProductList()
        })
      } else {
        console.log(productForm, '看看表单参数')
        //新增
        productApi.createProduct(productForm).then(res => {
          ElMessage.success('创建成功')
          open.value = false
          getProductList()
        })
      }
    }
  })
}

//新增商品
const handleCreate = () => {
  productForm.id = ''
  productForm.name = ''
  productForm.categoryId = ''
  productForm.price = 0
  productForm.stock = 0
  productForm.description = ''
  productForm.status = 1
  isEdit.value = false
  dialogTitle.value = '新增商品'
  open.value = true
}

//查询参数
const pageInfo = reactive({
  currentPage: 1,
  pageSize: 10,
  total: 0
})

//获取商品列表
const getProductList = () => {
  const params = {
    pageNum: pageInfo.currentPage,
    pageSize: pageInfo.pageSize,
    name: searchForm.name || null,
    categoryId: searchForm.categoryId || null,
  }
  productApi.getProductList(params).then(res => {
    productList.value = res.data.list
    pageInfo.total = res.data.total
  })
}

//获取分类列表
const getCategoryList = () => {
  categoryApi.getCategoryList().then(res => {
    categoryList.value = res.data
  })
}

//组件挂载时调用
onMounted(() => {
  getProductList()
  getCategoryList()
})
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>