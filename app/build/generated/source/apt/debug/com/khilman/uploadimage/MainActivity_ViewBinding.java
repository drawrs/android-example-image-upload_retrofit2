// Generated code from Butter Knife. Do not modify!
package com.khilman.uploadimage;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131492958;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btnCapturePicture, "field 'btnCapturePicture' and method 'onBtnCapturePictureClicked'");
    target.btnCapturePicture = Utils.castView(view, R.id.btnCapturePicture, "field 'btnCapturePicture'", Button.class);
    view2131492958 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnCapturePictureClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnCapturePicture = null;

    view2131492958.setOnClickListener(null);
    view2131492958 = null;
  }
}
