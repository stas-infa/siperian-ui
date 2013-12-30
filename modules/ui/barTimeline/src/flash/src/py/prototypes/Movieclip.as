/**
 * ...
 * @author DefaultUser (Tools -> Custom Arguments...)
 */

import flash.geom.Matrix;
 
class py.prototypes.Movieclip 
{
	private var _this:MovieClip;
	public function Movieclip() 
	{
	}
	
	public static function extend ()
	{
		MovieClip.prototype._drawHLine = function(x, y, w, c, s, a)
		{
			this.lineStyle(0, 0, 0);
			if (c == undefined) c = 0;
			if (a == undefined) a = 100;
			if (s == undefined) s = 1;		
			this.lineStyle(0, 0, 0);
			this.beginFill(c, a);
			this.moveTo(x, y);
			this.lineTo(x + w, y);
			this.lineTo(x + w, y + s);
			this.lineTo(x, y + s);
			this.lineTo(x, y);
			this.endFill();
		}
		
		MovieClip.prototype._drawVLine = function(x, y, h, c, s, a)
		{
			this.lineStyle(0, 0, 0);
			if (c == undefined) c = 0;
			if (a == undefined) a = 100;
			if (s == undefined) s = 1;
			this.beginFill(c, a);
			this.moveTo(x, y);
			this.lineTo(x + s, y);
			this.lineTo(x + s, y + h);
			this.lineTo(x, y + h);
			this.lineTo(x, y);
			this.endFill();
		}		
		// prototype that draws vertical dash-doted line
		// parameters
		// x1 - x value
		// y1 - start y value
		// y2 - end y value
		// p1 - dash size
		// p2 - space between dashes
		// off - y offset before first dash
		// s - dash-dot line height
		// c - line color
		// a - line alpha
		MovieClip.prototype._drawVDotLine = function(x1, y1, y2, p1, p2,offs,s,c,a)
		{
			if (offs==undefined) offs=0;
			if (s==undefined) s=1;
			if (c==undefined) c=0;	
			if (a==undefined) a=100;
			var ny = y1+offs;
			var ny1;
			while (ny!=y2)
			{
				y1 = (ny+p1>y2 ? y2-ny : p1)
				this._drawRect(x1,ny,s,y1,c,a)
				ny = ny+p1+p2; if (ny>y2) ny=y2;
			}
		}

		// prototype that draws rectangle
		// parameters
		// x - x value
		// y - y value
		// w - width
		// h - height
		// c - fill color
		// a - fill alpha
		// bs - border width
		// bc - border color
		// ba - border alpha
		MovieClip.prototype._drawRect = function (x:Number, y:Number, w:Number, h:Number, c:Number, a:Number, bs:Number, bc:Number, ba:Number)
		{
			if (bs == undefined) bs = -1;
			if (ba == undefined) ba = 100;
			if (a==undefined) a=100;
			
			this.lineStyle((bs<0 ? 0 : bs), bc, (bs<0 ? 0 : ba));
			this.moveTo (x, y);
			this.beginFill (c, a);
			this.lineTo (x+w, y);
			this.lineTo (x+w, y+h);
			this.lineTo (x, y+h);
			this.lineTo (x, y);		
			this.endFill();
		}

		MovieClip.prototype._drawRoundedRect = function (x:Number, y:Number, w:Number, h:Number, r:Number, fc:Number, fa:Number, matrix:Object, bs:Number, bc:Number, ba:Number):Void
		{
			if (fa == undefined) fa = 100;
			if (bs == undefined) bs = -1;
			if (ba == undefined) ba = 100;
			if (matrix==null || matrix==undefined) matrix=new Array(true,true,true,true);

			this.lineStyle((bs<0 ? 0 : bs), bc, (bs<0 ? 0 : ba));
			this.beginFill(fc, fa);
			if (matrix[0])
			{
				this.moveTo(x+r, y);
			} else
			{
				this.moveTo(x, y);
			}
			
			if (matrix[1])
			{
				this.lineTo(x+(w - r), y);
				this.curveTo(x+w, y, x+w, y+r);
			} else 
			{
				this.lineTo(x+w, y);
			}
			
			if (matrix[2])
			{
				this.lineTo(x+w, y+(h - r));
				this.curveTo(x+w, y+h, x+(w - r), y+h);
			} else
			{
				this.lineTo(x+w, y+h);
			}
			
			if (matrix[3])
			{
				this.lineTo(x+r, y+h);
				this.curveTo(x, y+h, x, y+(h - r));
			} else
			{
				this.lineTo(x, y+h);
			}

			if (matrix[0])
			{
				this.lineTo(x, y+r);
				this.curveTo(x, y, x+r, y);
			} else
			{
				this.lineTo(x, y);
			}
			this.endFill();
		}

		MovieClip.prototype._drawGradRoundedRect = function (x:Number, y:Number, w:Number, h:Number, r:Number, fc:Array, fa:Array, angle:Number, matrix:Object, bs:Number, bc:Number, ba:Number):Void
		{
			if (bs == undefined) bs = -1;
			if (ba == undefined) ba = 100;
			if (matrix==null || matrix==undefined) matrix=new Array(true,true,true,true);

			this.lineStyle((bs<0 ? 0 : bs), bc, (bs<0 ? 0 : ba));
			
			var mat:Matrix = new Matrix();
			mat.createGradientBox(w, h, angle/180*Math.PI,x,y);
			var ratios = new Array();
			ratios.push(0);
			var d:Number = 255/fc.length;
			for (var i=1;i<fc.length-1;i++)
			{
				ratios.push(Math.round(d*i));
			}
			ratios.push(255);

			this.beginGradientFill("linear", fc, fa, ratios, mat);

			if (matrix[0])
			{
				this.moveTo(x+r, y);
			} else
			{
				this.moveTo(x, y);
			}
			
			if (matrix[1])
			{
				this.lineTo(x+(w - r), y);
				this.curveTo(x+w, y, x+w, y+r);
			} else 
			{
				this.lineTo(x+w, y);
			}
			
			if (matrix[2])
			{
				this.lineTo(x+w, y+(h - r));
				this.curveTo(x+w, y+h, x+(w - r), y+h);
			} else
			{
				this.lineTo(x+w, y+h);
			}
			
			if (matrix[3])
			{
				this.lineTo(x+r, y+h);
				this.curveTo(x, y+h, x, y+(h - r));
			} else
			{
				this.lineTo(x, y+h);
			}

			if (matrix[0])
			{
				this.lineTo(x, y+r);
				this.curveTo(x, y, x+r, y);
			} else
			{
				this.lineTo(x, y);
			}
			this.endFill();
		}
		
		MovieClip.prototype._drawTriangle = function (x:Number, y:Number, w:Number, h:Number, c:Number, a:Number, bs:Number, bc:Number, ba:Number)
		{
			if (bs == undefined) bs = -1;
			if (ba == undefined) ba = 100;
			if (a==undefined) a=100;
			
			this.lineStyle((bs<0 ? 0 : bs), bc, (bs<0 ? 0 : ba));
			this.moveTo (x, y);
			this.beginFill (c, a);
			this.lineTo (x+w, y+h/2);
			this.lineTo (x, y+h);
			this.lineTo (x, y);		
			this.endFill();
		}

		MovieClip.prototype._createTextField = function (name:String,str:String, x:Number, y:Number, fontStyle:Object, depth:Number, embeddedFont:String):TextField
		{
			if (fontStyle==undefined) fontStyle=new Object({face:"Arial", size:12, color:0x0, bold:true, align:"left", html:false});
			else
			{
				if (fontStyle.face == undefined) fontStyle.face = "Arial";
				if (fontStyle.size == undefined) fontStyle.size = 11;
				if (fontStyle.color == undefined) fontStyle.color = 0x0;
				if (fontStyle.bold == undefined) fontStyle.bold = true;
				if (fontStyle.align == undefined) fontStyle.align = "left";
				if (fontStyle.html == undefined) fontStyle.html = false;
			}
			if (x==undefined) x = 0;
			if (y==undefined) y = 0;	
			var f:TextFormat = new TextFormat();
			f.font = fontStyle.face;
			f.size = fontStyle.size;
			f.color = fontStyle.color;
			f.bold = fontStyle.bold;
			f.align = fontStyle.align;
			if (fontStyle.leading!=undefined) f.leading = fontStyle.leading;

			this.createTextField(name,(depth==undefined ? this.getNextHighestDepth() : depth),x,y,16,16);
			var tf:TextField = this[name];
			tf.autoSize="left";
			tf.type="dynamic";
			tf.mouseWheelEnabled = false;
			tf.selectable = false;
			if (embeddedFont!=undefined && embeddedFont!="") {f.font=embeddedFont; tf.embedFonts = true;}
			tf.setNewTextFormat(f); 
			if (fontStyle.html)
			{
				tf.html = true;
				tf.multiline = true;
				tf.htmlText = '<font face="'+fontStyle.face+'" size="'+fontStyle.size+'">'+str+'</font>';
			} else tf.text = str;
			//tf.border=true;
			
			return tf;
			}
		}		
}