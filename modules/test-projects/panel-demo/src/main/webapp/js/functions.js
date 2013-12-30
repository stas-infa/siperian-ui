		
			function onResizePanel(){
	 var isMoz = navigator.userAgent.indexOf("Gecko") > - 1;
	//alert("OnResizeFunc");
		var panels =     document.getElementById('panelIds').value.split(',');
		//alert(panels.length);
		var panelReq=/sip\-pnl/i;

		for( var x = 0 ; x  < panels.length ; x++ ){
			//alert('panels id='+panels[x]);
			 var pDiv = document.getElementById(panels[x]);
			if ( pDiv.className.match(panelReq) ){
		
				if(pDiv.clientWidth!=void(0)){
						
					var mainWidth = new Number(pDiv.clientWidth);
					var mainHeight = new Number(pDiv.clientHeight);
					
					if( (mainWidth % 2) == 1 ){
						//alert(pDiv.clientWidth);
						var size = new Number((mainWidth / 2));
						var heightSize = new Number((mainHeight / 2 ) );
						var heightPlus = new Number(0);
						if( (mainHeight % 2 )  == 1 ){
							heightPlus = 1;
						}
					//alert('nethetnij razmer'+mainWidth);
						//setChildrenDivsSize(pDiv,size);
						var childReq=/spn_panel_bg/i;
						var divs= pDiv.getElementsByTagName("div");
						var divsArray = new Array();
						var cnt = 0;
						for( var i = 0 ; i < divs.length ; i ++ ){
							var cDiv = divs[i];
							if(cDiv.className.match(childReq)){
								divsArray[cnt++]=cDiv;
							}
						}
						var mozPlus = 0.5;
						if(isMoz){
							divsArray[0].style.width=(size+mozPlus)+"px";
							divsArray[1].style.width=(size - mozPlus)+"px";
							divsArray[2].style.width=(size+mozPlus)+"px";
							divsArray[3].style.width=(size - mozPlus)+"px";
						}else {
							//alert('ie');
							divsArray[0].style.width=(size)+"px";
							divsArray[1].style.width=(size)+"px";
							divsArray[2].style.width=(size)+"px";
							divsArray[3].style.width=(size)+"px";
						}
						
						
						divsArray[0].style.height=(heightSize)+"px";
						divsArray[1].style.height=(heightSize+heightPlus)+"px";
						divsArray[2].style.height=(heightSize+heightPlus)+"px";
						divsArray[3].style.height=(heightSize)+"px";
//						
//						divsArray[0].style.width=size;
//						divsArray[1].style.width=size+1;
//						divsArray[2].style.width=size;
//						divsArray[3].style.width=size+1;
//						alert('.currentStyle.height= '+divsArray[0].currentStyle.height);
//						alert('.currentStyle.height = '+divsArray[1].currentStyle.height);
//						alert('.Style.width = '+divsArray[0].width);
//						alert('.Style.width = '+divsArray[1].width);
						
						
					}else{
						//alert('ne thetnoe');
						var size = new Number((mainWidth / 2));
						var heightSize = new Number((mainHeight / 2 ) );

					//	alert('nethetnij razmer'+mainWidth);
						//setChildrenDivsSize(pDiv,size);
						var childReq=/spn_panel_bg/i;
						var divs= pDiv.getElementsByTagName("div");
						var divsArray = new Array();
						var cnt = 0;
						for( var i = 0 ; i < divs.length ; i ++ ){
							var cDiv = divs[i];
							if(cDiv.className.match(childReq)){
								divsArray[cnt++]=cDiv;
							}
						}
						divsArray[0].style.width=size+"px";
						divsArray[1].style.width=size+"px";
						divsArray[2].style.width=size+"px";
						divsArray[3].style.width=size+"px";
						
						
						divsArray[0].style.height=heightSize+"px";
						divsArray[1].style.height=heightSize+"px";
						divsArray[2].style.height=heightSize+"px";
						divsArray[3].style.height=heightSize+"px";
//						
				}
				}
			
			}
	}
	}
if(window.addEventListener){
	window.addEventListener('resize',function(){onResizePanel()},false);
}else if (window.attachEvent){
	window.attachEvent('onresize',function(){
		onResizePanel();
	});
}