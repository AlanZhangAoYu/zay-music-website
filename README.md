## Zay-Music 音乐网站

####  此项目为张傲宇个人设计项目(可能作为毕业设计)

- 一切以体现个人能力为目标
- 项目按照当前实际生产环境实现模块化、高并发等要求
- 如果条件允许，项目后期会自费租云服务器并部署上线

#### 技术栈

> Spring boot 2.6.3
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
>

#### 数据库建模

##### MySQL

- 管理员表(admin_tab)
  - admin_account 账号
  - admin_password 密码
  - admin_name 用户名
  
- 用户表(user_tab)

  - user_id 逻辑自增主键

  - user_account 账号（唯一约束）
  - user_password 密码
  - salt 加盐加密字符
  - user_name 用户名
  - user_sex 性别
  - user_birthday 出生日期
  - phone_number 手机号（唯一约束）
  - email 邮箱（唯一约束）
  - user_introduction 个人简介(个性签名)
  - user_location 用户所在地
  - head_img_id  头像文件id
  - create_time 账号创建时间
  - update_time 账号最近更新时间

- 歌曲表(song_tab)
  - song_id 歌曲id
  - song_name 歌曲名
  - singer_id 歌手id
  - album_id 所属专辑id
  - song_type 歌曲类型（华语、粤语、欧美、日韩、轻音乐、古典、其他）
  - create_time 创建时间
  - file_id 歌曲文件id
  
- 歌手表(singer_tab)
  - singer_id 歌手id
  - singer_name 歌手名
  - singer_sex 歌手性别
  - singer_birth 歌手生日
  - singer_introduction 歌手简介
  - singer_location 歌手所在地
  - singer_img_id 歌手图片文件id
  
- 专辑表(album_tab)
  - album_id 专辑id
  - album_name 专辑名称
  - singer_ids 歌手id列表
  - year 发布年份
  - album_img_url 专辑封面图片地址
  
- 音乐收藏表(collect_tab)
  
  - collect_id 逻辑自增主键
  
  - user_account 用户id
  - song_id 歌曲id
  - create_time 创建时间
  
- 歌曲评论表(song_comment_tab)
  
  - comment_id 逻辑自增主键
  
  - user_account 用户id
  - song_id 歌曲id
  - content 评论详情
  - create_time 评论时间
  
##### Redis

- 播放记录hash表(若用户重复播放历史记录，就修改本次播放时间为最新)
  
  - **Key**: user_account:song_id **Value**: song_id: {歌曲id} create_time: {最后播放时间}
- 歌曲播放量表(每当一首歌完整播放完一次，对应的歌曲的song_id:play_count加一)
  - **Key**: song_id:play_count **Value**:  完整播放次数
  - 那个有序集合zset又怎么设计????

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
