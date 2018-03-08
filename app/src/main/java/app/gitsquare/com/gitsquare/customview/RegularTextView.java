package app.gitsquare.com.gitsquare.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by admin on 13-01-2017.
 */

public class RegularTextView extends TextView {
    public RegularTextView(Context context) {
        super(context);
        setTypeface(context);
    }

    public RegularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public RegularTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }

    private void setTypeface(Context context)
    {
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"OpenSans-Regular.ttf"));
    }
}
