# birdEbook-Backend

```sql
DROP   SCHEMA witchtalk;

CREATE SCHEMA witchtalk DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

```







# 百科

权限：允许所有注册用户编辑

日志：需要日志记录用户编辑痕迹

用户：用户和论坛用户同步

后台：

1. table 所有的百科列表
2. 提供一个入口，允许用户使用Markdown 编辑器（类似于富文本）
3. 分类管理（admin权限）
4. 日志管理
5. 用户管理

前端：

1. 百科页面 也能直接编辑，需要把内容回显到编辑器上



http://aizuda.com/wiki/1083472/1084862





##  后台逻辑

1. [x] 分类（category）页面管理
2. [x] 标签（tag）页面管理  //待定，不确定和分类的区别
3. [x] wiki文章列表管理 ✅
4. [ ] wiki MK文本页面
5. [ ] 热门管理 hot 表
6. [ ] 用户管理列表（仅允许删除，不允许add）
7. [ ] 日志管理 (action not AOP) ✅
8. [ ] 图片上传 imgur 图床 (非关联上传完成，后期需要使用token俩关联图片和账户) ✅



## 前端

1. 分类（category）页面管理

2. 标签（tag）页面管理

3. wiki文章列表管理 ✅

4. wiki MK文本页面

5. 热门管理 hot 表

6. 用户管理列表

7. 日志管理 (action not AOP) ✅


1.

## 网站

1. [ ] 主页

2. [ ] 全局搜索

3. [ ] 文章页面

4. [ ] 评论功能

5. [ ] 浏览量统计

6. [ ] 热门


### 09/07/2023

#### Update

WikiArticle CRUD 简单测试

imgur图床图片上传



#### 新增需求

标签管理 / **分类管理**（categoryType） / 热门管理




###  10/07/2023

#### Update

WikiArticle DB change, userid --> created_by

create a new DB: category_type

#### Need

分类管理category， tree结构
<img width="256" alt="image" src="https://github.com/Amber916Young/birdEbook-Backend/assets/57694784/6ae81838-e15b-4c8f-9c09-85781f6f8ee9">



###  11/07/2023

#### Update

标签管理

####  14/07/2023

#### Update
新增两个API， 查询分类和标签
分类为树形结构
NEW 🆕 Needs新的需求

热门表

用户收藏表

热门分类表

###  17/07/2023

#### Update

需要记录标签/分类的使用次数，可能需要新建立一个中间表来记录每篇文章的标签使用次数，如果tags表更新，删除，关联表也需要做同样的操作（？）


## 数据结构参考
https://github.com/HelloGitHub-Team/Hydra/tree/main/hydra/tests/data

https://codebeautify.org/online-json-editor/y235386d6