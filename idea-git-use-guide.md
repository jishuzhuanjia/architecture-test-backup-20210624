### 记录idea git使用方法 及 使用期间出现错误的解决方法

#### 1.git合并时没有解决所有冲突会合并失败，此时仍然处在合并状态(Idea右下角提示Merging),需要手动取消:
    方式1: 命令行
    git merge --abort
    
    方式2: idea可视化操作
    右键项目 -> Git -> Repository -> Abort Merge

