  function setCaretToEnd (e) {

	  		//TODO nick - use Event.element(e)
            var control = $((e.target ? e.target : e.srcElement).id);

            if (control.createTextRange) {

                var range = control.createTextRange();

                range.collapse(false);

                range.select();

            }

            else if (control.setSelectionRange) {

                control.focus();

                var length = control.value.length;

                control.setSelectionRange(length, length);

            }

            control.selectionStart = control.selectionEnd = control.value.length;

        } 

//TODO nick - it's not the first PNGFIX function I see. Skipping until next interesting code block
function PNG(element,blankGif)
{
    if (/MSIE (5\.5|6).+Win/.test(navigator.userAgent))
    {
        var src;
        //alert(blankGif);
        if (element.tagName=='IMG')
        {
            if (/\.png.jsf$/.test(element.src))
            {
            	//alert('set img')
                src = element.src;
                element.src = blankGif;
            }
        }
        else
        {
            src = element.currentStyle.backgroundImage.match(/url\("(.+\.png)"\)/i)
            if (src)
            {
                src = src[1];
                element.runtimeStyle.backgroundImage="none";
            }
        }
      
        if (src){
        	// alert('apply For filter :'+blankGif);
        	 element.runtimeStyle.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + src + "',sizingMethod='scale')";
        }else{
        	//alert("failed: src="+src);
        }
    }
}