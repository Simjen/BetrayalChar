package com.betrayal.betrayalchar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

public class CardDialogFragment extends DialogFragment {
    private int image_id;
    public void setImage(int image_id){
        this.image_id = image_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_dialog, null);
        ImageView image = view.findViewById(R.id.card_view);

        Drawable drawable = getResources().getDrawable(image_id);

        image.setImageDrawable(drawable);
        return view;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Create the AlertDialog object and return it
        return dialog;
    }
}
