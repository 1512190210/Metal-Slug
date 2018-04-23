﻿#						Metal-Slug

#介绍：
#    本项目是一个简单的模仿合金弹头的小游戏，通过控制英雄移动射击躲避敌人子弹并消灭敌人。仿造小游戏特种任务（http://www.4399.com/flash/2472.htm)


#游戏及操作方式：
#     A: Move Left    D: Move Right    S:下蹲   J：射击    K：跳跃    Enter:开始以及重新开始游戏。进入游戏时请切换至英文输入法。英雄一共有三次生命（被子弹击中则损失一次生命），生命用完即重新开始游戏。

#游戏内对象介绍：
#    英雄：玩家操控可左右移动，下蹲，跳跃，射击。跳跃可越过敌人以及敌人子弹。
#    敌人（不包括坦克）：分为两种类型，第一种类型只会刺杀且不会移动：第二种类型会射击，刺杀并且会朝向英雄靠近
#    坦克：不会移动但会射出子弹，子弹位置较高可下蹲躲避
#    障碍物：木桶，可被英雄子弹破坏，阻碍英雄以及敌人的左右移动，英雄可跳跃越过障碍物
    
#原版已实现：
#    英雄的移动、射击、跳跃、下蹲，死亡动画，重生。 敌方的射击、移动、死亡动画、刺杀动作

#来自：https://github.com/finerc/Metal-Slug

#新增修改与优化：
#     1.修复了坦克无敌的问题并增加了坦克的死亡特效以及坦克射击效果（蹲下可躲避）
#     2.修复了左跳时英雄的朝向
#     3.将普通敌人修改为一击就死
#     4.优化英雄向左射击时子弹模型（朝向）的显示
#     5.增加了障碍物模型以及破坏后特效
#     6.增加了英雄对坦克以及英雄，敌人对障碍物的碰撞检测
#     7.增加了地图随着英雄的移动而移动并且敌人陆续出现
#     8.增加了游戏结束时候的统计框（统计击杀的敌人和坦克数量）
     
#不足之处：
#     1.动画效果不流畅
#     2.攻击方式，地形，敌人以及障碍物的种类单一
#     3.只设计1个关卡，且没有人物和难度选择，且关卡之间的连接关系尚未确定
#     4.判定机制不够完善（例如敌人/障碍物死亡/毁坏后在其死亡效果期间子弹仍然不能穿过，敌人刺杀时的判定不够精准）
#     5.缺乏游戏内互动（例如人质解救，武器更换，坦克和飞机控制）
    