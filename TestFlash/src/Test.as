/**
 *
 */
/**
 *
 */
 
class Test {
    private static var count:Number;
	private static var forwardBtn:Button;
	private static var backBtn:Button;
	private static var empty:MovieClip;
	private static var countText:TextField;

	public function Test() {
	   forwardBtn = _root.forwardBtn;
	   backBtn = _root.backBtn;
	   empty = _root.empty;
	   countText = _root.countText;
	   count = 1;
	   empty.loadMovie ("../images/pic_"+count+".jpg");
	   backBtn._visible = false;
	   count = 1;
	   empty.loadMovie("../images/pic_"+count+".jpg");
	   backBtn._visible = false;
	}

	public static function sqCount():Void {
	   forwardBtn.onPress = function() {
	       count++;
	       empty.loadMovie("../images/pic_"+count+".jpg");
	       backBtn._visible = true;
	       if (count>=3) {
	           this._visible = false;
	       } else {
	           this._visible = true;
	       }
	       countText.text = "Slide: "+count;
	   };
	}
	public static function ciCount():Void {
	   backBtn.onPress = function() {
	       count--;
	       empty.loadMovie("../images/pic_"+count+".jpg");
	       forwardBtn._visible = true;
	       if (count<=1) {
	           this._visible = false;
	       } else {
	           this._visible = true;
	       }
	       countText.text = "Slide: "+count;
	   };
	}

	
	public static function main() {
		var a:Test = new Test();
		a.sqCount();
        a.ciCount();
        background._alpha = 50;
	}

}

