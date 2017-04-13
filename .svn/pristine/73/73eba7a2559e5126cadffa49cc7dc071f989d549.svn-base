/*
 * 占位符提示插件
 * 1、引入placeHolderTips.js
 * 2、在需要提示的input框加入 tips="obj" obj为需要提示的语句
 */
var tips = {
	fix : function(){
		$("input[tips]").each(function(index, element){
			//获取需要提示的input
			var self = $(this), text = $(this).attr("tips");
			if(self.val() == '' && self.attr("ready") == undefined){
				//给原input框包一层div防止错位
				//self.wrap("<div></div>");
				var parent = $(this).parent();
				//创建替代input
				var replace = $("<input/>").val(text).attr("type", "text").addClass(self.attr("class")).removeClass("clear_box").css("color", "#aaaaaa");
				//replace.type = text;
				//replace.className = self.attr("class");
				//replace.value = text;
				self.hide();
				self.attr("ready", "yes");
				parent.find(self).after(replace);

				self.focusin(function(e) {
					self.show();
					replace.hide();
				}).focusout(function(e) {
					if(!self.val()){
						self.hide();
						replace.show();
					}
				});
				replace.click(function(e) {
					replace.hide();
					self.focus();
				});
			}
		});
	}
};
jQuery(function(){
	tips.fix();
});
