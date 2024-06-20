# Manhunt

猎人游戏升级版，主要特性包括：

- 指向逃脱者的指南针
- 指南针指向以逃脱者为圆心一定半径内的随机点
- 游戏自动开始
- 末影龙死亡检测

## 命令

### 设置逃脱者

```
/manhunt set runner 玩家ID
```

### 设置猎人

```
/manhunt set hunter 玩家ID
```

### 查看分队情况

```
/manhunt list
```

### 开始游戏

```
/manhunt start
```

## 插件配置

请看`config.yml`，它应该在插件文件夹下的`ManhuntPlusPlus`文件夹内。

```
# 是否赋予猎人指南针获取到的实际位置一个偏移量
DoRandomOffset: true
# 随机偏移最大偏移大小
RandomOffsetRadius: 10
# 猎人更新手中的指南针时是否告知指南针指向的具体坐标
SendCompassPointToExact: true

# 猎人开始游戏时是否获取指南针
GetCompassOnGameStart: true

# 逃脱者开始逃跑时是否自动开始
AutoStartGame: true
# 逃脱者逃跑判定半径
AutoStartRadius: 5

# 逃脱者胜利条件
# 可设置的值有：ENDER_DRAGON（末影龙死亡）
#          END_POEM（看到终末之诗，即进入返回主世界的末地传送门）
RunnerWinCondition: END_POEM
```
