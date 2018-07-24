package com.pchmn.materialchips.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pchmn.materialchips.R;
import com.pchmn.materialchips.model.ChipInterface;
import com.pchmn.materialchips.util.ColorUtil;
import com.pchmn.materialchips.util.LetterTileProvider;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created on 6/11/18.
 *
 * @author sdelaysam
 */
public class PinkDetailedChipView extends DetailedChipView {

    // xml elements
    ViewGroup mContentLayout;
    CircleImageView mAvatarIconImageView;
    TextView mNameTextView;
    TextView mInfoTextView;
    ImageButton mDeleteButton;
    // letter tile provider
    private static LetterTileProvider mLetterTileProvider;
    // attributes
    private ColorStateList mBackgroundColor;


    public PinkDetailedChipView(Context context) {
        super(context);
        init(null);
    }

    public PinkDetailedChipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @Override
    protected void init(AttributeSet attrs) {
        super.init(attrs);
        // inflate layout
        View rootView = inflate(getContext(), R.layout.detailed_chip_view, this);
        mContentLayout = rootView.findViewById(R.id.content);
        mAvatarIconImageView = rootView.findViewById(R.id.avatar_icon);
        mNameTextView = rootView.findViewById(R.id.name);
        mInfoTextView = rootView.findViewById(R.id.info);
        mDeleteButton = rootView.findViewById(R.id.delete_button);
        // letter tile provider
        mLetterTileProvider = new LetterTileProvider(getContext());

    }

    @Override
    public ViewGroup getContentLayout() {
        return mContentLayout;
    }

    @Override
    public void setOnDeleteClicked(OnClickListener onClickListener) {
        mDeleteButton.setOnClickListener(onClickListener);
    }

    public void setAvatarIcon(Drawable icon) {
        mAvatarIconImageView.setImageDrawable(icon);
    }

    public void setAvatarIcon(Bitmap icon) {
        mAvatarIconImageView.setImageBitmap(icon);
    }

    public void setAvatarIcon(Uri icon) {
        mAvatarIconImageView.setImageURI(icon);
    }

    public void setName(String name) {
        mNameTextView.setText(name);
    }

    public void setInfo(String info) {
        if(info != null) {
            mInfoTextView.setVisibility(VISIBLE);
            mInfoTextView.setText(info);
        }
        else {
            mInfoTextView.setVisibility(GONE);
        }
    }

    public void setTextColor(ColorStateList color) {
        mNameTextView.setTextColor(color);
        mInfoTextView.setTextColor(ColorUtil.alpha(color.getDefaultColor(), 150));
    }

    public void setBackGroundcolor(ColorStateList color) {
        mBackgroundColor = color;
        mContentLayout.getBackground().setColorFilter(color.getDefaultColor(), PorterDuff.Mode.SRC_ATOP);
    }

    public int getBackgroundColor() {
        return mBackgroundColor == null ? ContextCompat.getColor(getContext(), R.color.colorAccent) : mBackgroundColor.getDefaultColor();
    }

    public void setDeleteIconColor(ColorStateList color) {
        mDeleteButton.getDrawable().mutate().setColorFilter(color.getDefaultColor(), PorterDuff.Mode.SRC_ATOP);
    }

    public static class Builder {
        private Context context;
        private Uri avatarUri;
        private Drawable avatarDrawable;
        private String name;
        private String info;
        private ColorStateList textColor;
        private ColorStateList backgroundColor;
        private ColorStateList deleteIconColor;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder avatar(Uri avatarUri) {
            this.avatarUri = avatarUri;
            return this;
        }

        public Builder avatar(Drawable avatarDrawable) {
            this.avatarDrawable = avatarDrawable;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder info(String info) {
            this.info = info;
            return this;
        }

        public Builder chip(ChipInterface chip) {
            this.avatarUri = chip.getAvatarUri();
            this.avatarDrawable = chip.getAvatarDrawable();
            this.name = chip.getLabel();
            this.info = chip.getInfo();
            return this;
        }

        public Builder textColor(ColorStateList textColor) {
            this.textColor = textColor;
            return this;
        }

        public Builder backgroundColor(ColorStateList backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder deleteIconColor(ColorStateList deleteIconColor) {
            this.deleteIconColor = deleteIconColor;
            return this;
        }

        public PinkDetailedChipView build() {
            return PinkDetailedChipView.newInstance(this);
        }
    }

    private static PinkDetailedChipView newInstance(Builder builder) {
        PinkDetailedChipView detailedChipView = new PinkDetailedChipView(builder.context);
        // avatar
        if(builder.avatarUri != null)
            detailedChipView.setAvatarIcon(builder.avatarUri);
        else if(builder.avatarDrawable != null)
            detailedChipView.setAvatarIcon(builder.avatarDrawable);
        else
            detailedChipView.setAvatarIcon(mLetterTileProvider.getLetterTile(builder.name));

        // background color
        if(builder.backgroundColor != null)
            detailedChipView.setBackGroundcolor(builder.backgroundColor);

        // text color
        if(builder.textColor != null)
            detailedChipView.setTextColor(builder.textColor);
        else if(ColorUtil.isColorDark(detailedChipView.getBackgroundColor()))
            detailedChipView.setTextColor(ColorStateList.valueOf(Color.WHITE));
        else
            detailedChipView.setTextColor(ColorStateList.valueOf(Color.BLACK));

        // delete icon color
        if(builder.deleteIconColor != null)
            detailedChipView.setDeleteIconColor(builder.deleteIconColor);
        else if(ColorUtil.isColorDark(detailedChipView.getBackgroundColor()))
            detailedChipView.setDeleteIconColor(ColorStateList.valueOf(Color.WHITE));
        else
            detailedChipView.setDeleteIconColor(ColorStateList.valueOf(Color.BLACK));

        detailedChipView.setName(builder.name);
        detailedChipView.setInfo(builder.info);
        return detailedChipView;
    }

}
