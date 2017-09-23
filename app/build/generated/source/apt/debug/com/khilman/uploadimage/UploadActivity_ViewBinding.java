// Generated code from Butter Knife. Do not modify!
package com.khilman.uploadimage;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UploadActivity_ViewBinding implements Unbinder {
  private UploadActivity target;

  private View view2131492963;

  @UiThread
  public UploadActivity_ViewBinding(UploadActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UploadActivity_ViewBinding(final UploadActivity target, View source) {
    this.target = target;

    View view;
    target.imgPreview = Utils.findRequiredViewAsType(source, R.id.imgPreview, "field 'imgPreview'", ImageView.class);
    target.videoPreview = Utils.findRequiredViewAsType(source, R.id.videoPreview, "field 'videoPreview'", VideoView.class);
    target.txtPercentage = Utils.findRequiredViewAsType(source, R.id.txtPercentage, "field 'txtPercentage'", TextView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    view = Utils.findRequiredView(source, R.id.btnUpload, "field 'btnUpload' and method 'onViewClicked'");
    target.btnUpload = Utils.castView(view, R.id.btnUpload, "field 'btnUpload'", Button.class);
    view2131492963 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    UploadActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imgPreview = null;
    target.videoPreview = null;
    target.txtPercentage = null;
    target.progressBar = null;
    target.btnUpload = null;

    view2131492963.setOnClickListener(null);
    view2131492963 = null;
  }
}
