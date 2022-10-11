package com.example.ssleepy.ui.pager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ssleepy.R;
import com.example.ssleepy.ui.controls.ShutdownFragment;
import com.example.ssleepy.ui.media.ControlsFragment;
import com.example.ssleepy.ui.keyboard.KeyboardFragment;
import com.example.ssleepy.ui.mouse.MouseFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final Fragment[] fragments = new Fragment[] {
            new ControlsFragment(),
            new ShutdownFragment(),
            new MouseFragment(),
            new KeyboardFragment()
    };

    public final String[] fragmentNames = new String[] {
            "Controls",
            "Shutdown",
            "Mouse",
            "Keyboard"
    };

    public final int[] fragmentIcons = new int[] {
            R.drawable.ic_computer,
            R.drawable.ic_shutdown,
            R.drawable.ic_menu_mouse,
            R.drawable.ic_keyboard
    };

    public ViewPagerAdapter(FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }
}
