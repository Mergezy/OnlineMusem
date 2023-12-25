package com.example.onlinemusem;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MuseumAdapter museumAdapter;
    private List<MuseumItem> museumItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        museumItems = generateSampleData();
        museumAdapter = new MuseumAdapter(this, museumItems);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(museumAdapter);

        // Добавление слушателя для обработки нажатий на элементы списка
        museumAdapter.setOnItemClickListener((position, view) -> {
            MuseumItem selectedItem = museumItems.get(position);

            // Создание Intent для запуска DetailActivity
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("imageResourceId", selectedItem.getImageResourceId());
            intent.putExtra("description", selectedItem.getDescription());
            startActivity(intent);
        });
    }

    private List<MuseumItem> generateSampleData() {
        List<MuseumItem> items = new ArrayList<>();
        items.add(new MuseumItem("Painting 1", "Description 1", R.mipmap.images));
        items.add(new MuseumItem("Painting 2", "Description 1", R.mipmap.image2));
        items.add(new MuseumItem("Painting 1", "Икона является одной из древнейших русских домонгольских икон. По-видимому, еще в XVI веке произведение оказалось в Москве и на протяжении последующих столетий входило в убранство одного из храмов Московского Кремля. Возможно, оно было привезено из Новгорода в годы правления Ивана Грозного, неоднократно разорявшего этот город и вывозившего в столицу все его важнейшие святыни. Учитывая основные направления богословской мысли позднего XII века, можно думать, что в древности икона была частью оглавного деисусного чина, написанного на отдельных досках и предназначенного для убранства темплона алтарной преграды.\n" +
                "\n" +
                "Монументальность образа, тип широкого скульптурно вылепленного лика с крупными чертами и полными щеками, рисунок увеличенных миндалевидных глаз обнаруживают разительное сходство с отдельными ликами росписей капеллы Богородицы на Патмосе. Но еще ближе стилю и образу иконы одна из манер в росписи новгородской церкви Спаса Преображения на Нередице (1199). Не исключено, что «Ангел Златые власы» был одной из деисусных икон, стоявших на алтарной преграде этого храма. Историческая ситуация в Новгороде на рубеже XII–XIII веков, сильное влияние монументального искусства Византии объясняют появление в местной культуре памятника, отразившего ведущие стилистические течения и художественные вкусы, сложившиеся в столице в эпоху около 1200 года. Русский музей. От иконы до современности. СПб. 2015. С. 38.\n" +
                "\n" +
                "Древнейшая икона в собрании Русского музея, является одним из самых ярких памятников домонгольского времени. Большинство исследователей относят это произведение к новгородской школе иконописи. Икона изображает Архангела Гавриила, который \"един от великих князей небесных ... искони посылаем был Богом на Землю, он принес Адаму в Рай заповедь не вкушать от дерева познания добра и зла, ... он благовествовал Марии\".\n" +
                "\n" +
                "Характер образа и особенности письма свидетельствуют о глубокой связи с традициями искусства Византии. Золотые нити волос – символ величия и бессмертия в искусстве античного мира. Икона, очевидно, предназначалась для алтарной преграды и входила в состав деисусного чина.\n" +
                "\n" +
                "Деисус (греч. – моление) – композиция из трех (или более) икон, воплощающая мысль о заступничестве Богоматери, Иоанна Предтечи, архангелов Михаила и Гавриила, других святых перед Христом за грешное человечество. Икона реставрирована до 1926.", R.mipmap.ikon));

        return items;
    }
}
