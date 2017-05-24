package com.anghatechsolution.eww.epoojastore.Fragment;


        import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
        import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.LinearSnapHelper;
        import android.support.v7.widget.RecyclerView;

        import android.support.v7.widget.SnapHelper;
        import android.support.v7.widget.Toolbar;
import android.util.Log;

      import android.view.Gravity;
       import android.view.LayoutInflater;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.ViewGroup;

        import android.view.ViewTreeObserver;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;
        import android.widget.ScrollView;
        import android.widget.Toast;
        import com.anghatechsolution.eww.epoojastore.Adapter.SliderLayout;
        import com.anghatechsolution.eww.epoojastore.Adapter.SnapAdapter;
        import com.anghatechsolution.eww.epoojastore.Animations.DescriptionAnimation;

        import com.anghatechsolution.eww.epoojastore.R;
import com.anghatechsolution.eww.epoojastore.SliderTypes.BaseSliderView;
import com.anghatechsolution.eww.epoojastore.SliderTypes.TextSliderView;
import com.anghatechsolution.eww.epoojastore.Tricks.ViewPagerEx;
import com.anghatechsolution.eww.epoojastore.model.App;

        import com.anghatechsolution.eww.epoojastore.model.Snap;
        import com.anghatechsolution.eww.epoojastore.utils.CustomScrollView;
        import com.anghatechsolution.eww.epoojastore.utils.JBHeaderScroll;


        import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import timber.log.Timber;



/**
 * Created by eww on 3/23/2017.
 */

public class Mainfragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private RecyclerView mRecyclerView;
    private SliderLayout mDemoSlider;
    ScrollView scrollView;
    LinearLayout linearLayout;
    private  Toolbar toolbar;
    private boolean mHorizontal;


    private final String LOG_TAG = "ScrollViewDemoActivity";

    public static final String ORIENTATION = "orientation";





    public static Mainfragment newInstance() {
        Mainfragment main_fragment = new Mainfragment();
        return main_fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        Timber.d("%s - OnCreateView", this.getClass().getSimpleName());
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(mRecyclerView);
       /* toolbar = (Toolbar) view.findViewById(R.id.fragemtn_toolbar);*/
        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);

       /* scrollView=(ScrollView)view.findViewById(R.id.fragmentScroll);
        linearLayout=(LinearLayout)view.findViewById(R.id.fragmentLinearLayout);
        scrollView.removeAllViews();
        scrollView.addView(linearLayout);
        scrollView.setSmoothScrollingEnabled(true);*/
        Log.d("MainFragment", "MDemoSlider");

        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("New Arivivels", "https://www.epoojastore.com/image/cache/catalog/Banners/April-2017/2-870x433.jpg");
        url_maps.put("Pooja Needs", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("New", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("All", "http://mulugu.com/mobile-audio/mobileSlideBanners/mobileSlideBanners_1.jpg");


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);

        if (savedInstanceState == null) {
            mHorizontal = true;
        } else {
            mHorizontal = savedInstanceState.getBoolean(ORIENTATION);
        }


        setupAdapter();


        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView.image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);


            mDemoSlider.addSlider(textSliderView);
            Log.d("MainFragment", "NameAdd");
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

     /* final Toolbar toolbar= new Toolbar(getActivity());
        toolbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scrollView.setY(toolbar.getHeight());
                mJBHeaderScroll = new JBHeaderScroll(toolbar, 0);

                mJBHeaderScroll.registerScroller(scrollView, new JBHeaderScroll.IJBHeaderScroll() {
                    @Override
                    public void onReposition(float top, boolean scrollingUp, float scrollDelta) {
                        // The list's view top edge must be adjusted during scrolling.
                        // IMPORTANT: Make sure you use the correct type of LayoutParams which is the type that applies to the parent
                        // container of the listview.

                        // When the user releases their finger while scrolling very slowly, the jitter from their finger
                        // may result in a slight amount of scrolling downward. This can result in a side effect of the
                        // header animating down when it might have animated up depending on its current position. To
                        // avoid this, avoid repositioning the scroller for small amounts of scrolling. You may need to
                        // play with this value.

                        if (scrollingUp || (!scrollingUp && (scrollDelta > 5))) {
                           RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                            layoutParams.setMargins(0, (int) top, 0, 0);
                            scrollView.setLayoutParams(layoutParams);
                            toolbar.bringToFront(); // Necessary if your scroller is rendered last.
                        }
                    }

                    @Override
                    public int onHeaderBeforeAnimation(boolean scrollingUp, float scrollDelta) {
                        return JBHeaderScroll.ANIMATE_HEADER_USE_DEFAULT;
                    }

                    @Override
                    public void onHeaderAfterAnimation(boolean animatedUp, float scrollDelta) {
                    }
                });

                scrollView.setJBHeaderRef(mJBHeaderScroll);
                toolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });*/


        return view;
    }




    private void setupAdapter() {
        List<App> apps = getApps();

        SnapAdapter snapAdapter = new SnapAdapter();
        if (mHorizontal) {

            snapAdapter.addSnap(new Snap(Gravity.CENTER_HORIZONTAL, "SPECIAL ITEMS", apps));
            snapAdapter.addSnap(new Snap(Gravity.START, "DEVOTIONAL PHOTOS", apps));
            snapAdapter.addSnap(new Snap(Gravity.END, "HOMAS", apps));
            snapAdapter.addSnap(new Snap(Gravity.CENTER_HORIZONTAL, "POOJA SAMAGRI KITS", apps));
            snapAdapter.addSnap(new Snap(Gravity.CENTER_HORIZONTAL, "BOOKS", apps));
        } else {
            snapAdapter.addSnap(new Snap(Gravity.CENTER_VERTICAL, "Snap center", apps));
            snapAdapter.addSnap(new Snap(Gravity.TOP, "Snap top", apps));
            snapAdapter.addSnap(new Snap(Gravity.BOTTOM, "Snap bottom", apps));
        }

        mRecyclerView.setAdapter(snapAdapter);
    }

    public List<App> getApps()
    {
        List<App> apps = new ArrayList<>();
        apps.add(new App("Google+", R.drawable.ic_google_48dp, 4.6f));
        apps.add(new App("Gmail", R.drawable.ic_gmail_48dp, 4.8f));
        apps.add(new App("Inbox", R.drawable.ic_inbox_48dp, 4.5f));
        apps.add(new App("Google Keep", R.drawable.ic_keep_48dp, 4.2f));
        apps.add(new App("Google Drive", R.drawable.ic_drive_48dp, 4.6f));
        apps.add(new App("Hangouts", R.drawable.ic_hangouts_48dp, 3.9f));
        apps.add(new App("Google Photos", R.drawable.ic_photos_48dp, 4.6f));
        apps.add(new App("Messenger", R.drawable.ic_messenger_48dp, 4.2f));
        apps.add(new App("Sheets", R.drawable.ic_sheets_48dp, 4.2f));
        apps.add(new App("Slides", R.drawable.ic_slides_48dp, 4.2f));
        apps.add(new App("Docs", R.drawable.ic_docs_48dp, 4.2f));
        return apps;

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ORIENTATION, mHorizontal);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = ((AppCompatActivity) getActivity());
            if (activity.getSupportActionBar() != null)
                activity.getSupportActionBar().setTitle(R.string.home_fragment);
        }
    }






    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getActivity(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state)
    {

    }




}
