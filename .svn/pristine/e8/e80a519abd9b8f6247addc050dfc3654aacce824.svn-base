package com.sgfm.datacenter.util;


public class TradingItemParameter {
	/**联赛名称:如 英格兰JPT锦标赛*/
	public String matchTypeName;
	/**赛事名称：如 史云顿 VS 班列特*/
	public String matchName;
	/**交易项名称：如 大(0-0.5)	*/
	public String tradingItemName;
	/**是否滚球：1是 0不是*/
	public int rollFlag;
	/**时间范围：1全场 2上半场*/
	public Long dateRange;
	/**玩法名称：让球 allow 大小球 bigsmall*/
	public String playName;

	public TradingItemParameter(String matchTypeName, String matchName,
			String tradingItemName, int rollFlag, Long dateRange,
			String playName) {
		this.matchTypeName = matchTypeName;
		this.matchName = matchName;
		this.tradingItemName = tradingItemName;
		this.rollFlag = rollFlag;
		this.dateRange = dateRange;
		this.playName = playName;
	}
	
	/**
	 * 生成KEY
	 * @return
	 */
	public String genKey(){
		StringBuilder key = new StringBuilder();
		if("allow".equals(this.playName) || "bigsmall".equals(this.playName)){
	    	String[] attenders = this.matchName.split(" VS ");
	    	char itemName = this.tradingItemName.charAt(0);
	    	String index = null;
	    	if("bigsmall".equals(this.playName)){
	    		index = this.tradingItemName.substring(1,this.tradingItemName.length());
	    	}else{
	    		index = this.tradingItemName.substring(1,this.tradingItemName.length()-1);
	    	}
	    	
	    	key.append(this.matchTypeName).append("▲");
	    	key.append(this.matchName).append("▲");
	    	key.append(this.dateRange).append("▲");
	    	switch(itemName){
		    	case '主':
		    		key.append(attenders[0]);
		    		break;
		    	case '客':
		    		key.append(attenders[1]);
		    		break;
		    	case '大':
		    	case '小':
		    		key.append(itemName);
		    		break;
	    	}
	    	int k = 0;
	    	
	    	String symbol="";
	    	if("allow".equalsIgnoreCase(this.playName)){
	    		
	    		if('-'==index.charAt(0)){
	    			k=2;
	    			symbol = "+";
	    		}else{
	    			k=1;
	    			symbol = "-";
	    		}
	    	}else if("bigsmall".equalsIgnoreCase(this.playName.trim())){
	    		k=0;
	    	}
	    	key.append(" ");
	    	key.append(symbol);
	    	
	    	String[] indexTypes = index.substring(k,index.length()).split("-");
	    	for(int i=0;i<indexTypes.length;i++){
	  			float temp = Float.valueOf(indexTypes[i]);
				indexTypes[i]= temp==0 ? 0+"" : temp+"" ;
	    	}
	    	
	    	String newIndex = null;
	    	if(indexTypes.length==2){
	    		newIndex = indexTypes[0]+"&"+indexTypes[1];
	    	}else{
	    		newIndex = indexTypes[0];
	    	}
	    	key.append( newIndex ).append("▲");
	    	key.append(this.rollFlag);
	    	return key.toString();
		}else{
			return null;
		}
	}

	@Override
	public String toString() {
		return "TradingItemParameter [matchTypeName=" + matchTypeName
				+ ", matchName=" + matchName + ", tradingItemName="
				+ tradingItemName + ", rollFlag=" + rollFlag + ", dateRange="
				+ dateRange + ", playName=" + playName + "]";
	}
	
	public static void main(String[] args) {
		String index = "5.20-0.5";
    	String[] indexTypes = index.split("-");
    	for(int i=0;i<indexTypes.length;i++){
    		String[] temps = indexTypes[i].split("\\.");
    		if(temps.length==2&&Float.valueOf(temps[1])==0){
    			indexTypes[i]=indexTypes[i].substring(0, indexTypes[i].indexOf("."));
    		}else if(temps.length>=2){
    			float temp = Float.valueOf(indexTypes[i]);
				indexTypes[i]= temp==0 ? 0+"" : temp+"" ;
    		}
    	}
    	String newIndex = null;
    	if(indexTypes.length==2){
    		newIndex = indexTypes[0]+"&"+indexTypes[1];
    	}else{
    		newIndex = indexTypes[0];
    	}
    	System.out.println(newIndex);
		
	}
	
}