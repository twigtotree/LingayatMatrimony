package com.twigtotree.lingayatmatrimony.Signup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.twigtotree.lingayatmatrimony.Mainflow.MainViewActivity;
import com.twigtotree.lingayatmatrimony.R;
import com.twigtotree.lingayatmatrimony.SignUpActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by sgoud1 on 7/10/16.
 */
public class PhotoUploadFragment extends android.support.v4.app.Fragment {

    private int SELECT_FILE = 8007;
    Button getfromgallery ;
    Button uploadToFirebaseButton;
    Button doneWithSignUp;
    Context mApplicationContext;
    ImageView userImage;

    public PhotoUploadFragment(){
    }

    public PhotoUploadFragment(Context applicationContext) {
        mApplicationContext = applicationContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.signup_upload_photo, container, false);

        getfromgallery = (Button)rootView.findViewById(R.id.getfromgallery);
        userImage = (ImageView)rootView.findViewById(R.id.userphoto);
        uploadToFirebaseButton = (Button)rootView.findViewById(R.id.uploadphoto);
        doneWithSignUp = (Button)rootView.findViewById(R.id.done_with_sign_up);
        doneWithSignUp.setVisibility(View.VISIBLE);
        getfromgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhotoFromGallery();
            }
        });

        uploadToFirebaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadToFirebase();
            }
        });
        doneWithSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainViewActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    private void uploadToFirebase() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
         String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

//        StorageReference storageRef = storage.getReferenceFromUrl("gs://matrimony-a2334.appspot.com");
//        StorageReference imagesRef = storageRef.child("images");
//        StorageReference spaceRef = storageRef.child("images/space.jpg");
//
//        mountainsRef.getName().equals(mountainImagesRef.getName());    // true
//        mountainsRef.getPath().equals(mountainImagesRef.getPath());

        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("gs://matrimony-a2334.appspot.com");

// Create a reference to "mountains.jpg"
        StorageReference mountainsRef = storageRef.child(userId+".jpg");

// Create a reference to 'images/mountains.jpg'
        StorageReference mountainImagesRef = storageRef.child("images/"+userId+".jpg");

// While the file names are the same, the references point to different files
        mountainsRef.getName().equals(mountainImagesRef.getName());    // true
        mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false

        userImage.setDrawingCacheEnabled(true);
        userImage.buildDrawingCache();
        Bitmap bitmap = userImage.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                uploadToFirebaseButton.setVisibility(View.INVISIBLE);
                doneWithSignUp.setVisibility(View.VISIBLE);

            }
        });
    }

    private void choosePhotoFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
        }
    }

    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(mApplicationContext.getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userImage.setImageBitmap(bm);
        getfromgallery.setVisibility(View.INVISIBLE);
        uploadToFirebaseButton.setVisibility(View.VISIBLE);


    }

}
