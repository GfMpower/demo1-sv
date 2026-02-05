<template>
  <div style="padding: 20px">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>分类管理</span>
          <el-button type="primary" @click="handleCreate">新增分类</el-button>
        </div>
      </template>

      <el-table :data="categoryList" row-key="id" style="width: 100%;"
                :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
      >
        <el-table-column prop="name" label="分类名称"></el-table-column>
        <el-table-column prop="level" label="层级"></el-table-column>
        <el-table-column prop="sort" label="排序"></el-table-column>
        <el-table-column prop="description" label="描述"></el-table-column>
        <el-table-column prop="status" label="状态">
          <template #default="scope">
            <el-tag>
              {{ scope.row.status === 1 ? '正常' : '禁用' }}
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

    </el-card>

    <!-- 新增或者编辑对话框 -->
    <el-dialog
        :title="dialogTitle"
        v-model="open"
        width="500px"
        @close=""
    >
      <el-form
          ref="categoryFormRef"
          :model="categoryForm"
          :rules="rules"
          label-width="100px"
      >
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="categoryForm.name" placeholder="请输入分类名称"/>
        </el-form-item>

        <el-form-item label="父级分类" prop="parentId">
          <el-select v-model="categoryForm.parentId" placeholder="请选择父级分类">
            <el-option label="顶级分类" :value="0"/>
            <el-option
                v-for="category in categoryOptions"
                :key="category.id"
                :label="category.name"
                :value="category.id"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="排序" prop="sort">
          <el-input-number v-model="categoryForm.sort" :min="0"/>
        </el-form-item>

        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="categoryForm.status">
            <el-radio :value=1>正常</el-radio>
            <el-radio :value=0>禁用</el-radio>
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
import categoryApi from '@/api/category.js'
import {ElMessage, ElMessageBox} from "element-plus";

//分类表单参数
let categoryForm = reactive({
  id: '',
  name: '',
  parentId: 0,
  sort: 0,
  description: '',
  status: 1,
})

//表单验证规则
const rules = reactive({
  name: [
    {required: true, message: '请输入分类名称', trigger: 'blur'}
  ],
  parentId: [
    {required: true, message: '选择父级分类', trigger: 'change'}
  ],
  sort: [
    {required: true, message: '请输入排序值', trigger: 'blur'}
  ],
})

//对话框title
const dialogTitle = ref('')

//对话框是否打开
const open = ref(false)

//表格数据
const categoryList = ref([])

//是否编辑状态
const isEdit = ref(false)

const categoryFormRef = ref()

//编辑分类
const handleEdit = (row) => {
  isEdit.value = true
  categoryForm = row
  open.value = true
}

//删除分类
const handleDelete = (row) => {
  ElMessageBox.confirm(
      '确定要删除该条分类信息吗?',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  )
      .then(() => {
        categoryApi.deleteCategory(row.id).then(res => {
          getCategoryList()
          ElMessage({type: 'success', message: '删除成功',})
        })
      })
      .catch(() => {
        ElMessage({type: 'info', message: '取消删除',})
      })
}

//提交表单
const submitForm = () => {
  categoryFormRef.value.validate((valid, fields) => {
    if (valid) {
      if (isEdit.value) {
        //编辑
        categoryApi.updateCategory(categoryForm).then(res => {
          ElMessage.success('修改成功')
          open.value = false
          getCategoryList()
        })
      } else {
        //新增
        categoryApi.createCategory(categoryForm).then(res => {
          ElMessage.success('创建成功')
          open.value = false
          getCategoryList()
        })
      }
    }
  })
}

//新增分类
const handleCreate = () => {
  isEdit.value = false
  dialogTitle.value = '新增分类'
  open.value = true
  getCategoryOptions()
}

const categoryOptions = ref(null)

//获取分类选项 (用于父级分类选择使用)
const getCategoryOptions = () => {
  categoryApi.getCategoryList().then(res => {
    categoryOptions.value = res.data
  })
}

//获取分类列表
const getCategoryList = () => {
  categoryApi.getCategoryTree().then(res => {
    categoryList.value = res.data
  })
}

//组件挂载时调用
onMounted(() => {
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
