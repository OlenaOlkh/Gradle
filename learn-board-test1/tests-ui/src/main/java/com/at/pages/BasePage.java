package com.at.pages;

import com.at.pages.fragments.HeaderFragment;
import lombok.Getter;

@Getter
public class BasePage extends AbstractPage {

    public HeaderFragment getHeaderFragment() {
        return getFragmentFactory().createFragment(HeaderFragment.class);
    }

}
