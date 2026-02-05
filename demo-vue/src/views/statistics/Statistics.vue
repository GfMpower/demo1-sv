<template>
  <div style="padding: 20px">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>数据统计</span>
        </div>
      </template>

      <el-form :inline="true" :model="salesForm" style="margin-bottom: 20px">
        <el-form-item label="统计周期">
          <el-select style="width: 200px;" v-model="salesForm.days" placeholder="请选择统计周期">
            <el-option v-for="item in dayOptions"
                       :key="item.value"
                       :label="item.lable"
                       :value="item.value"
            />
          </el-select>
        </el-form-item>
      </el-form>

      <el-row :gutter="24">
        <el-col :span="8">
          <el-card class="stat-card">
            <div class="stat-content">
              <div class="stat-icon" style="color: #409eff">
                <el-icon>
                  <User/>
                </el-icon>
              </div>
              <div class="stat-info">
                <div class="stat-title">总用户数</div>
                <div class="stat-number">{{ salesData.totalUsers }}</div>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="stat-card">
            <div class="stat-content">
              <div class="stat-icon" style="color: #409eff">
                <el-icon>
                  <Postcard/>
                </el-icon>
              </div>
              <div class="stat-info">
                <div class="stat-title">总订单数</div>
                <div class="stat-number">{{ salesData.totalOrders }}</div>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="stat-card">
            <div class="stat-content">
              <div class="stat-icon" style="color: #409eff">
                <el-icon>
                  <Money/>
                </el-icon>
              </div>
              <div class="stat-info">
                <div class="stat-title">总销售额</div>
                <div class="stat-number">{{ salesData.totalSales + '元' }}</div>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>

      <!-- 销售趋势图表 -->
      <div style="height: 400px;margin-top: 20px">
        <v-chart style="height: 100%; width: 100%;" :option="salesChartOption" autoresize/>
      </div>

    </el-card>
  </div>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {Money, Postcard, User} from "@element-plus/icons-vue";
import statisticsApi from "@/api/statistics.js";

//销售统计数据
const dayOptions = [
  {lable: '最近7天', value: 7},
  {lable: '最近30天', value: 30},
  {lable: '最近90天', value: 90},
]

const salesForm = reactive({
  days: 7
})

const salesData = ref({
  totalUsers: 0,
  totalOrders: 0,
  totalSales: 0,
  dailySalesList: []
})

//调用API查询相关数据
const loadInfo = () => {
  statisticsApi.getSalesStatistics({days: salesForm.days}).then(res => {
    salesData.value = res.data
    //更新销售趋势图表
    updateSalesChart(res.data)
  })
}

const updateSalesChart = (data) => {
  //提取日期 销售额 订单数
  const dates = data.dailySalesList.map(item => item.date)
  const sales = data.dailySalesList.map(item => item.sales)
  const orders = data.dailySalesList.map(item => item.orders)

  //更新图表配置
  salesChartOption.value.xAxis.data = dates
  salesChartOption.value.series[0].data = sales
  salesChartOption.value.series[1].data = orders



}

//销售趋势图表配置
const salesChartOption = ref({
  title: {
    text: '销售趋势'
  },
  tooltip: {
    trigger: 'axis'
  },
  legend: {
    data: ['销售额', '订单数']
  },
  xAxis: {
    type: 'category',
    data: []
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      name: '销售额',
      type: 'line',
      data: [],
      smooth: true,
      itemStyle: {
        color: '#409EFF'
      }
    },
    {
      name: '订单数',
      type: 'line',
      data: [],
      smooth: true,
      itemStyle: {
        color: '#67C23A'
      }
    }
  ]
})

//组件加载时调用
onMounted(() => {
  loadInfo()
})
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.stat-card {
  border-radius: 8px;
}

.stat-content {
  display: flex;
  align-items: center;
}

.stat-info {
  flex: 1;
}

.stat-title {
  font-size: 14px;
  color: #999;
  margin-bottom: 5px;
}

.stat-number {
  font-size: 24px;
  font-weight: bold;
  color: #333;
}

.stat-icon {
  font-size: 32px;
  margin-right: 15px;
}
</style>
