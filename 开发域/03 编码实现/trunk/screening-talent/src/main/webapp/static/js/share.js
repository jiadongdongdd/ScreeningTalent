	$("#qzone").bind("click",function(){
			/*$.ajax({
				url:getRootPath()+'/share/qzone',
				type:"get",
				contentType: "application/json; charset=utf-8", 
				success:function(data){
					window.open(data.detail, 'newwindow',
					'height=400,width=400,top=100,left=100');
				}
			});*/
		var share1 = new ShareTip();
			share1.sharetoqqzone(
				"这是一本关于地图故事的集锦，以一本书的形式来展示地图故事，我们可以使用鼠标拖动来翻页，或者点击翻页按钮，点击 马上体验，可以体验故事，同时在触屏上也有很好的体验效果奥，地图故事，尽在书中，赶快体验吧，建议使用对 HTML5支持较好的浏览器",
				"http://www.xsifter.com",
				"http://tm.arcgisonline.cn:8038/App101/MapstoryBook/css/Img/ShareBook.jpg");
		});
		
		$("#sina").bind("click",function(){
			/*$.ajax({
				url:getRootPath()+"/share/sina",
				type:"get",
				contentType: "application/json; charset=utf-8", 
				success:function(data){
					window.open(data.detail,'newwindow',
					'height=500,width=400,top=100,left=100');
				}
			});*/
			var share1 = new ShareTip();
			share1.sharetosina("这是一本关于地图故事的集锦，以一本书的形式来展示地图故事，我们可以使用鼠标拖动来翻页，或者点击翻页按钮，点击 马上体验，可以体验故事，同时在触屏上也有很好的体验效果奥，地图故事，尽在书中，赶快体验吧，建议使用对 HTML5支持较好的浏览器",
					"http://www.xsifter.com/","http://tm.arcgisonline.cn:8038/App101/MapstoryBook/css/Img/ShareBook.jpg");
		});
		$("#wechant").bind("click",function(){
//					window.open(getRootPath()+"/share/payQR", 'newwindow',
//					'height=400,width=400,top=100,left=100');
			
			wx.ready(function(Api) {
			       // 微信分享的数据

			           var wxData = {

			               "appId" : 'wx34353453332',

			               "imgUrl" : 'http://apppic/show.jpg',

			               "link" : '链接地址',

			               "desc" : "小伙伴们，我正在参加感恩节抽奖活动，希望能中个iPhone6，快来帮帮我吧!",

			               "title" : "感恩节礼物 -- iPhone6 抽到就是你的"

			           };

			           // 分享的回调

			           var wxCallbacks = {

			               // 分享操作开始之前

			               ready : function() {

			                   // 你可以在这里对分享的数据进行重组

			               },

			               // 分享被用户自动取消

			               cancel : function(resp) {

			                   // 你可以在你的页面上给用户一个小Tip，为什么要取消呢？

			               },

			               // 分享失败了

			               fail : function(resp) {

			                   // 分享失败了，是不是可以告诉用户：不要紧，可能是网络问题，一会儿再试试？

			               },

			               // 分享成功

			               confirm : function(resp) {

			               },

			               // 整个分享过程结束

			               all : function(resp, shareTo) {

			                   // 如果你做的是一个鼓励用户进行分享的产品，在这里是不是可以给用户一些反馈了？

			               }

			           };

			           // 用户点开右上角popup菜单后，点击分享给好友，会执行下面这个代码

			           Api.shareToFriend(wxData, wxCallbacks);

			           // 点击分享到朋友圈，会执行下面这个代码

			           Api.shareToTimeline(wxData, wxCallbacks);

			           // 点击分享到腾讯微博，会执行下面这个代码

			           Api.shareToWeibo(wxData, wxCallbacks);

			       });
		});
		
		function getRootPath() {
			//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
			var curWwwPath = window.document.location.href;
			//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
			var pathName = window.document.location.pathname;
			var pos = curWwwPath.indexOf(pathName);
			//获取主机地址，如： http://localhost:8083
			var localhostPaht = curWwwPath.substring(0, pos);
			//获取带"/"的项目名，如：/uimcardprj
			var projectName = pathName
					.substring(0, pathName.substr(1).indexOf('/') + 1);
			return (localhostPaht + projectName);
		}
 		
		var ShareTip = function() {

		};
		//分享到腾讯微博
		ShareTip.prototype.sharetoqq = function(content, url, picurl) {
			var shareqqstring = 'http://v.t.qq.com/share/share.php?title='
					+ content + '&url=' + url + '&pic=' + picurl;
			window.open(shareqqstring, 'newwindow',
					'height=100,width=100,top=100,left=100');
		};
		//分享到新浪微博
		ShareTip.prototype.sharetosina = function(title, url, picurl) {
			var sharesinastring = 'http://v.t.sina.com.cn/share/share.php?title='
					+ title
					+ '&url='
					+ url
					+ '&content=utf-8&sourceUrl='
					+ url
					+ '&pic=' + picurl;
			window.open(sharesinastring, 'newwindow',
					'height=400,width=400,top=100,left=100');
		};
		//分享到QQ空间
		ShareTip.prototype.sharetoqqzone = function(title, url, picurl) {
			var shareqqzonestring = 'http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?summary='
					+ title + '&url=' + url + '&pics=' + picurl;
			window.open(shareqqzonestring, 'newwindow',
					'height=400,width=400,top=100,left=100');
		};
 		/*var share1 = new ShareTip();
 					share1.sharetoqqzone(
 						"这是一本关于地图故事的集锦，以一本书的形式来展示地图故事，我们可以使用鼠标拖动来翻页，或者点击翻页按钮，点击 马上体验，可以体验故事，同时在触屏上也有很好的体验效果奥，地图故事，尽在书中，赶快体验吧，建议使用对 HTML5支持较好的浏览器",
 						"http://tm.arcgisonline.cn:8038/App101/MapstoryBook/Default.html",
 						"http://tm.arcgisonline.cn:8038/App101/MapstoryBook/css/Img/ShareBook.jpg");*/

// 		   		share1.sharetoqq("这是一本关于地图故事的集锦，以一本书的形式来展示地图故事，我们可以使用鼠标拖动来翻页，或者点击翻页按钮，点击 马上体验，可以体验故事，同时在触屏上也有很好的体验效果奥，地图故事，尽在书中，赶快体验吧，建议使用对 HTML5支持较好的浏览器","http://tm.arcgisonline.cn:8038/App101/MapstoryBook/Default.html","http://tm.arcgisonline.cn:8038/App101/MapstoryBook/css/Img/ShareBook.jpg");
//
// 		   		share1.sharetosina("这是一本关于地图故事的集锦，以一本书的形式来展示地图故事，我们可以使用鼠标拖动来翻页，或者点击翻页按钮，点击 马上体验，可以体验故事，同时在触屏上也有很好的体验效果奥，地图故事，尽在书中，赶快体验吧，建议使用对 HTML5支持较好的浏览器","http://192.168.1.21:8080/xsifter/","http://tm.arcgisonline.cn:8038/App101/MapstoryBook/css/Img/ShareBook.jpg");

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		