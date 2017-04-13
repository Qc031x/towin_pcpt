/*
* jQuery pager plugin
*/
(function($) {
	
	var info = {
			"first":"首页",
			"prev":"上一页",
			"next":"下一页",
			"last":"尾页",
			"allPage":"总共{0}条记录(共{1}页)"
			};
			
	$(document).ready(function(){
		//从页面获取国际化信息		
		if(window.sgfm && window.sgfm.pagerInfo){
			$.extend(info, sgfm.pagerInfo);
		}	
	});		
	
    $.fn.sgfmpager = function(options) {
        var opts = $.extend({}, $.fn.sgfmpager.defaults, options);

        return this.each(function() {

        // empty out the destination element and then render out the pager with the supplied options
            //$(this).empty().append(renderpager(parseInt(options.pagenumber), parseInt(options.pagecount), options.buttonClickCallback));
			$(this).empty();
            renderpager.call(this,parseInt(options.pagenumber), parseInt(options.pagecount), options.buttonClickCallback, parseInt(options.totalrows));
            // specify correct cursor activity
            //$('.pages li').mouseover(function() { document.body.style.cursor = "pointer"; }).mouseout(function() { document.body.style.cursor = "auto"; });
        });
    };

    // render and return the pager with the supplied options
    function renderpager(pagenumber, pagecount, buttonClickCallback,totalrows) {

        // setup $pager to hold render
        var $pager = $(this),currentButton;

        // add in the previous and next buttons
        $pager.append(renderButton('first', pagenumber, pagecount, buttonClickCallback)).append(renderButton('prev', pagenumber, pagecount, buttonClickCallback));

        // pager currently only handles 10 viewable pages ( could be easily parameterized, maybe in next version ) so handle edge cases
        var startPoint = 1;
        var endPoint = 7;

        if (pagenumber > 3) {
            startPoint = pagenumber - 3;
            endPoint = pagenumber + 3;
        }

        if (endPoint > pagecount) {
            startPoint = pagecount - 6;
            endPoint = pagecount;
        }

        if (startPoint < 1) {
            startPoint = 1;
        }

		if(startPoint > 1){
			currentButton = $('<a href="javascript:void(0)"  class=" ">1...</a>');
            startPoint == pagenumber ? currentButton.addClass('active') : currentButton.click(function() {buttonClickCallback(1); });
            currentButton.appendTo($pager);
			++startPoint;
		}
		
        // loop thru visible pages and render buttons
        for (var page = startPoint; page < endPoint; page++) {

            currentButton = $('<a href="javascript:void(0)" class=" ">' + (page) + '</a>');

            page == pagenumber ? currentButton.addClass('active') : currentButton.click(function() { buttonClickCallback(this.firstChild.data); });
            currentButton.appendTo($pager);
        }
		
		if(endPoint < pagecount){
			currentButton  = $('<a href="javascript:void(0)" class=" ">...'+pagecount+'</a>');
		}else{
			currentButton = $('<a href="javascript:void(0)" class=" ">'+endPoint+'</a>');;
		}
		
        pagecount == pagenumber ? currentButton.addClass('active') : currentButton.click(function() { buttonClickCallback(pagecount); });
        currentButton.appendTo($pager);
		
        // render in the next and last buttons before returning the whole rendered control back.
        $pager.append(renderButton('next', pagenumber, pagecount, buttonClickCallback)).append(renderButton('last', pagenumber, pagecount, buttonClickCallback));
		
		//show the pagecount
		$pager.prepend("&nbsp;&nbsp;"+replaceString(info.allPage,[totalrows,pagecount])+"&nbsp;&nbsp;");
		//goto the defined page
		$pager.append($('<input type="text" id="txt_go" name="txt_go" size=1 class="t_input" /> ').val(pagenumber)) 
			  .append('<input type="button" name="btn_go" id="btn_go" value="GO" />')
			  .find(":button[id=btn_go]")
			  .click(function(){
							var goPage = $(this).siblings(":text[id=txt_go]").attr("value");
							var reg = /[^\d]/g;
							
							if(reg.test(goPage)){
									$(this).siblings(":text[id=txt_go]").attr("value", pagenumber);
									return false;
								}
							else{
									if(goPage == ""){
										return false;
									}
									if(goPage < 1){
										goPage = 1;
									}
						
									if(goPage > pagecount){
										goPage = pagecount;
									}
									buttonClickCallback(goPage);
								}
							
						});
	
        return $pager;
    }

    // renders and returns a 'specialized' button, ie 'next', 'previous' etc. rather than a page number button
    function renderButton(buttonLabel, pagenumber, pagecount, buttonClickCallback) {
		
//		var info = {
//			"first":"首页",
//			"prev":"上一页",
//			"next":"下一页",
//			"last":"尾页"
//			};

        var $Button = $('<a href="javascript:void(0)" class="next">' + info[buttonLabel] + '</a>');

        var destPage = 1;

        // work out destination page for required button type
        switch (buttonLabel) {
            case "first":
                destPage = 1;
                break;
            case "prev":
                destPage = pagenumber - 1;
                break;
            case "next":
                destPage = pagenumber + 1;
                break;
            case "last":
                destPage = pagecount;
                break;
        }

        // disable and 'grey' out buttons if not needed.
        if (buttonLabel == "first" || buttonLabel == "prev") {
            pagenumber <= 1 ? $Button.addClass('pgEmpty') : $Button.click(function() { buttonClickCallback(destPage); });
        }
        else {
            pagenumber >= pagecount ? $Button.addClass('pgEmpty') : $Button.click(function() { buttonClickCallback(destPage); });
        }

        return $Button;
    }

    // pager defaults. hardly worth bothering with in this case but used as placeholder for expansion in the next version
    $.fn.sgfmpager.defaults = {
        pagenumber: 1,
        pagecount: 1
    };
	
	function replaceString(str,ay){
		$.each(ay,function(i){
			str=str.replace(new RegExp("\\{"+i+"\\}","g"),this);
		});
		return str;
	}

})(jQuery);





