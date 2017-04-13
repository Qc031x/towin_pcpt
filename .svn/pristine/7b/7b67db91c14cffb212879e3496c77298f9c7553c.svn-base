package com.sgfm.datacenter.util;

/**
 * 数据中心发送JMS消息队列名.<br>
 * 
 * @author Feiqiumin
 * @date 2011-6-20
 */
public enum QueueEnum {
    QUEUE_STOCK("queue_match")// 大盘管理队列
    , QUEUE_MATCH("queue_match")// 赛事管理队列
    , QUEUE_SYSPARAM("queue_sysparam") // 系统参数
    , QUEUE_SETTLEMENT("queue_settlement")// 结算
    , QUEUE_USER("queue_user")// 用户、角色、权限队列
    , QUEUE_INTENTION_CONTRACT("queue_intention_contract")// 交易意向及合同交易系统使用
    , QUEUE_DICT("queue_dict")// 数据字典管理队列
    , QUEUE_INTERNATION("queue_match")// 国际化资源队列
    , QUEUE_STOCK_DATA("queue_stock_data")// 盘口数据同步专用队列
    , QUEUE_MATCH_ORIGINALITEN("match.originalIten") // 持久化初始意向
    , QUEUE_MATCH_CONTRACTITEN("match.contractIten") // 持久化合同和认购意向
    , QUEUE_SBF_REALINFO("sbf.match.realinfo")// 盘中信息
    , QUEUE_SBF_MATCH("sbf.match.baseinfo") // 基础资料
    , QUEUE_SBF_WAGER("queue_bet_history") // 第三方历史注单
    , QUEUE_BET_NOTIFY("bet.notify") // 操盘系统消息通知
    , QUEUE_HEART_BEAT_TRADE("heartBeat.trade")//交易系统心跳包
    , QUEUE_THIRD_RATIO("third_ratio"); //交易系统第三方赔率
    // , QUEUE_MATCHREALINFO("queue_MatchRealInfo");

    private String value;

    QueueEnum(String value) {
        this.value = value;
    }

    public String get() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }

}