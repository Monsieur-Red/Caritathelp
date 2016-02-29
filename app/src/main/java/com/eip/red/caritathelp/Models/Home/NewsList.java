package com.eip.red.caritathelp.Models.Home;

import com.eip.red.caritathelp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 11/01/2016.
 */
public class NewsList {

    private List<News> news;

    public NewsList() {
        news = new ArrayList<News>();

        news.add(new News(R.drawable.logo_caritathelp_2017_picture_only, "Nam sed eros quis nisl", "01/01/2016", "Sodales dapibus eu accumsan libero. Duis a egestas risus. Nulla mattis vel nisl id rutrum. Sed eget mi nisl. Duis aliquam elit at lectus eleifend ultrices."));
        news.add(new News(R.drawable.logo_restos_du_coeur, "Nam sed eros quis nisl", "05/01/2016", "Sodales dapibus eu accumsan libero. Duis a egestas risus. Nulla mattis vel nisl id rutrum. Sed eget mi nisl. Duis aliquam elit at lectus eleifend ultrices."));
        news.add(new News(R.drawable.logo_croix_rouge, "Nam sed eros quis nisl", "10/01/2016", "Sodales dapibus eu accumsan libero. Duis a egestas risus. Nulla mattis vel nisl id rutrum. Sed eget mi nisl. Duis aliquam elit at lectus eleifend ultrices."));
        news.add(new News(R.drawable.logo_caritathelp_2017_picture_only, "Nam sed eros quis nisl", "01/01/2016", "Sodales dapibus eu accumsan libero. Duis a egestas risus. Nulla mattis vel nisl id rutrum. Sed eget mi nisl. Duis aliquam elit at lectus eleifend ultrices."));
        news.add(new News(R.drawable.logo_restos_du_coeur, "Nam sed eros quis nisl", "05/01/2016", "Sodales dapibus eu accumsan libero. Duis a egestas risus. Nulla mattis vel nisl id rutrum. Sed eget mi nisl. Duis aliquam elit at lectus eleifend ultrices."));
        news.add(new News(R.drawable.logo_croix_rouge, "Nam sed eros quis nisl", "10/01/2016", "Sodales dapibus eu accumsan libero. Duis a egestas risus. Nulla mattis vel nisl id rutrum. Sed eget mi nisl. Duis aliquam elit at lectus eleifend ultrices."));
        news.add(new News(R.drawable.logo_caritathelp_2017_picture_only, "Nam sed eros quis nisl", "01/01/2016", "Sodales dapibus eu accumsan libero. Duis a egestas risus. Nulla mattis vel nisl id rutrum. Sed eget mi nisl. Duis aliquam elit at lectus eleifend ultrices."));
        news.add(new News(R.drawable.logo_restos_du_coeur, "Nam sed eros quis nisl", "05/01/2016", "Sodales dapibus eu accumsan libero. Duis a egestas risus. Nulla mattis vel nisl id rutrum. Sed eget mi nisl. Duis aliquam elit at lectus eleifend ultrices."));
        news.add(new News(R.drawable.logo_croix_rouge, "Nam sed eros quis nisl", "10/01/2016", "Sodales dapibus eu accumsan libero. Duis a egestas risus. Nulla mattis vel nisl id rutrum. Sed eget mi nisl. Duis aliquam elit at lectus eleifend ultrices."));
    }

    public List<News> getNews() {
        return news;
    }

    public News getNews(int id) {
        return (news.get(id));
    }
}
