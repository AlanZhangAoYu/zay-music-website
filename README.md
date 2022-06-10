## Zay-Music 音乐网站

####  此项目为张傲宇个人设计项目(可能作为毕业设计)

- 一切以体现个人能力为目标
- 项目按照当前实际生产环境实现模块化、高并发等要求
- 如果条件允许，项目后期会自费租云服务器并部署上线

#### 技术栈

> Springboot
>
> Vue3
>
> Element-plus
>
> MySQL
>
> Mongodb
>
> Redis

#### 需求分析



#### 数据库建模
##### MySQL

- 管理员表(admin_tab)

  | 字段 | 类型 | 备注 |
  | :----- | :----- | ------ |
  | admin_account | 中等文本 | 账号 |
  | admin_password | 短文本 | 密码 |
  | admin_name |  | 用户名 |

- 用户表(user_tab)

  | 字段              | 类型 | 备注                         |
  | ----------------- | ---- | ---------------------------- |
  | user_id           |      | 逻辑自增主键（雪花算法生成） |
  | user_account      |      | 账号（存在唯一约束）         |
  | user_password     |      | 密码                         |
  | salt              |      | 盐加密字符（随机六位字符）   |
  | user_name         |      | 用户名                       |
  | user_sex          |      | 性别（男、女、保密）         |
  | user_birthday     |      | 出生日期                     |
  | phone_number      |      | 手机号（唯一约束）           |
  | email             |      | 邮箱（唯一约束）             |
  | user_introduction |      | 个人简介（个性签名）         |
  | user_location     |      | 用户所在地                   |
  | head_img_id       |      | 头像图片文件ID               |
  | create_time       |      | 账号创建时间                 |
  | update_time       |      | 账号最近更新时间             |

- 歌曲表(song_tab)
  
  | 字段        | 类型 | 备注                                                         |
  | ----------- | ---- | ------------------------------------------------------------ |
  | song_id     |      | 歌曲ID（自增主键）                                           |
  | song_name   |      | 歌曲名                                                       |
  | singer_id   |      | 歌手ID（逻辑上的外键，实际数据库中没有，参考《阿里巴巴Java开发手册》，以下皆同） |
  | album_id    |      | 所属专辑ID（逻辑上的外键）                                   |
  | song_type   |      | 歌曲所属类型ID（逻辑上的外键）                               |
  | create_time |      | 创建时间                                                     |
  | file_id     |      | 歌曲文件ID                                                   |
  
- 歌手表(singer_tab)
  
  | 字段                | 类型 | 备注               |
  | ------------------- | ---- | ------------------ |
  | singer_id           |      | 歌手ID（自增主键） |
  | singer_name         |      | 歌手名             |
  | singer_sex          |      | 歌手性别           |
  | singer_birth        |      | 歌手生日           |
  | singer_introduction |      | 歌手简介           |
  | singer_location     |      | 歌手所在地         |
  | singer_img_id       |      | 歌手图片文件ID     |
  
- 专辑表(album_tab)
  
  | 字段          | 类型 | 备注                   |
  | ------------- | ---- | ---------------------- |
  | album_id      |      | 专辑ID（自增主键）     |
  | album_name    |      | 专辑名称               |
  | singer_id     |      | 歌手ID（逻辑上的外键） |
  | year          |      | 发布年份               |
  | album_img_url |      | 专辑封面图片地址       |
  
- 音乐收藏表(collect_tab)
  
  | 字段        | 类型 | 备注                         |
  | ----------- | ---- | ---------------------------- |
  | collect_id  |      | 逻辑自增主键（雪花算法生成） |
  | user_id     |      | 用户ID（逻辑上的外键）       |
  | song_id     |      | 歌曲ID（逻辑上的外键）       |
  | create_time |      | 创建时间                     |
  
- 歌曲评论表(song_comment_tab)
  
  | 字段              | 类型 | 备注                           |
  | ----------------- | ---- | ------------------------------ |
  | comment_id        |      | 逻辑自增主键（雪花算法生成）   |
  | user_id           |      | 用户ID（逻辑上的外键）         |
  | comment_type      |      | 评论对象类型ID（逻辑上的外键） |
  | comment_object_id |      | 评论对象ID（逻辑上的外键）     |
  | content           |      | 评论详情                       |
  | create_time       |      | 评论时间                       |
  
  注：以后可增加评论点赞数等需求
  
- 歌曲种类表(song_type_tab)
  
  | 字段           | 类型 | 备注                   |
  | -------------- | ---- | ---------------------- |
  | song_type_id   |      | 歌曲种类ID（自增主键） |
  | song_type_name |      | 歌曲种类名称           |
  
  注：暂定种类有
  
  > 未知分类、华语、粤语、欧美、日韩、轻音乐、古典、其他 
  
- 评论种类表(comment_type_tab)

  | 字段              | 类型 | 备注                         |
  | ----------------- | ---- | ---------------------------- |
  | comment_type_id   |      | 评论对象的类别ID（自增主键） |
  | comment_type_name |      | 评论对象名                   |

  注：暂定种类有

  > 歌曲评论、歌手评论、专辑评论

  以后可增加

  > MV评论、歌单评论等

##### Redis

- 歌曲播放量表(每当一首歌完整播放完一次，对应的歌曲的song_id:play_count加一)
  - **Key**: song_id:play_count **Value**:  完整播放次数
  - 那个有序集合zset又怎么设计????（人菜）
- 验证码
  - **Key**: user_account **Value**: 验证码的正确结果 **存活时间**: 2min

##### MongoDB

- 文件模型表(MongoDB)
  - file_id 文件id
  - file_name 文件名称
  - content_type 文件类型
  - size 文件大小
  - upload_date 上传时间
  - md5 文件md5值
  - content 文件内容
  - path 文件路径
