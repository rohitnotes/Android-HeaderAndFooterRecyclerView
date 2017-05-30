package com.takwolf.android.hfrecyclerviewdemo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public final class ApiClient {

    private ApiClient() {}

    private static final Illust[] illusts = {
            new Illust(UUID.randomUUID().toString(), "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3896789573,1597189089&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=312981972,2966942205&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1526505593,3178206108&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1772627212,406756128&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1503321342,94041873&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2934886526,1332211774&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1122067212,2150264722&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1343672975,2037573637&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3772557563,1176127479&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1280764982,3745501831&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1486752105,4254843220&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1707239401,3106035130&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3077686375,3188923106&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1562285892,1325423991&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3661648148,612075559&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2414464581,254199628&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3907852375,4117062581&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1440793564,2331979170&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=120376652,2243603085&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1421089861,3387353081&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2974606076,876377175&fm=23&gp=0.jpg"),
            new Illust(UUID.randomUUID().toString(), "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=917773156,1903857270&fm=23&gp=0.jpg")
    };

    private static final Random random = new Random();

    public static List<Illust> buildIllustList(int size) {
        List<Illust> illustList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int position = Math.abs(random.nextInt()) % illusts.length;
            illustList.add(illusts[position]);
        }
        return illustList;
    }

}
