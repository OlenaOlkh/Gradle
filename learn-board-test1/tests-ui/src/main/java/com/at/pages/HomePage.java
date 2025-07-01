package com.at.pages;

import com.at.pages.fragments.CalendarFragment;
import com.at.pages.fragments.CalendarHeaderFragment;
import com.at.pages.fragments.neweventcardform.NewEventCardFormFragment;
import com.at.utils.annotations.PageUrl;
import lombok.Getter;

@PageUrl("/\\?period=\\d{4}-\\d{2}-\\d{2}T\\d{2}(:|%3A)\\d{2}(:|%3A)\\d{2}Z&period=\\d{4}-\\d{2}-\\d{2}T\\d{2}(:|%3A)\\d{2}(:|%3A)\\d{2}Z")
@Getter
public class HomePage extends BasePage {

    public CalendarHeaderFragment getCalendarHeaderFragment() {
        return getFragmentFactory().createFragment(CalendarHeaderFragment.class);
    }

    public CalendarFragment getCalendarFragment() {
        return getFragmentFactory().createFragment(CalendarFragment.class);
    }

    public NewEventCardFormFragment getNewEventCardFormFragment() {
        return getFragmentFactory().createFragment(NewEventCardFormFragment.class);
    }

}