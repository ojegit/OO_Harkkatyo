package com.example.oo_harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.Cartesian;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.Pie;
import com.anychart.anychart.ValueDataEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Statistics extends AppCompatActivity {

    public static enum Statistic {
        COUNT,
        NO_ROUNDS,
        NO_LOSSES,
        NO_WINS,
        NO_MATCHES,
        EXP_BY_TRAINING,
        EXP_BY_FIGHTING,
        EXP,
        NO_TRAINED,
        NO_REVIVES,
        NO_LEVELS,
        NO_ATTACKS,
        NO_DEFENCES,

    }

    //aggregated color and statistic
    private HashMap<Lutemon.Color, HashMap<Statistics.Statistic, Double>> statisticsPerColor;
    //aggregated by statistic
    private HashMap<Statistics.Statistic, Double> overallStatistics;


    public Statistics() {
        this.statisticsPerColor = new HashMap<>();
        this.overallStatistics = new HashMap<>();

        //Run only ONCE!
        initStatisticsPerColor(statisticsPerColor);
        initOverallStatistics(overallStatistics);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        //statistics per color
        calcStatisticsPerColor();

        //overall statistics
        calcOverallStatistics();


        //
        //3x2 GRID LAYOUT
        //
        //[1,1] Lutemon Counts By Color
        countsByColorBarChart(R.id.any_chart_view_11);

        //[1,2] Lutemon Counts By State
        countsByStateBarChart(R.id.any_chart_view_12);

        //[2,1] Wins By Color, Pie Chart (and win%)
        winsByColorPieChart(R.id.any_chart_view_21);

        //[2,2] Losses By Color, Pie Chart (and loss%)
        lossesByColorPieChart(R.id.any_chart_view_22);

        //[3,1]
        //[3,2]

    }

    public void countsByStateBarChart(int id) {
        Cartesian barChart = AnyChart.bar();
        List<DataEntry> data = new ArrayList<>();

        HashMap<Lutemon.LutemonState,ArrayList<Lutemon>> lutemonsByState = Storage.getInstance().getLutemonsByState();

        for(Lutemon.LutemonState state : Lutemon.LutemonState.values()) {
            data.add(new ValueDataEntry(state.toString(), lutemonsByState.get(state).size()));
        }

        barChart.setTitle("Lutemon Counts By State");
        barChart.data(data);
        AnyChartView anyChartView = (AnyChartView) findViewById(id);
        anyChartView.setChart(barChart);
    }

    public void winsByColorPieChart(int id) {
        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();
        for(Lutemon.Color color : Lutemon.Color.values()) {

            data.add(new ValueDataEntry(color.toString(), statisticsPerColor.get(color).get(Statistic.NO_WINS)));

        }
        pie.setTitle("Wins By Color");
        pie.data(data);
        AnyChartView anyChartView = (AnyChartView) findViewById(id);
        anyChartView.setChart(pie);
    }

    public void lossesByColorPieChart(int id) {
        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();
        for(Lutemon.Color color : Lutemon.Color.values()) {
            data.add(new ValueDataEntry(color.toString(), statisticsPerColor.get(color).get(Statistic.NO_LOSSES)));
        }
        pie.setTitle("Losses By Color");
        pie.data(data);
        AnyChartView anyChartView = (AnyChartView) findViewById(id);
        anyChartView.setChart(pie);
    }

   public void countsByColorBarChart(int id) {
       Cartesian barChart = AnyChart.bar();
       List<DataEntry> data = new ArrayList<>();
       for(Lutemon.Color color : Lutemon.Color.values()) {
           data.add(new ValueDataEntry(color.toString(), statisticsPerColor.get(color).get(Statistic.COUNT)));
       }
       barChart.setTitle("Lutemon Counts");
       barChart.data(data);
       AnyChartView anyChartView = (AnyChartView) findViewById(id);
       anyChartView.setChart(barChart);
   }


    //initialize
    public void initStatisticsPerColor(HashMap<Lutemon.Color, HashMap<Statistics.Statistic, Double>> statsPerColor) {
        for(Lutemon.Color color : Lutemon.Color.values()) {
            statsPerColor.put(color, new HashMap<Statistics.Statistic, Double>());
            for(Statistics.Statistic statistic : Statistics.Statistic.values()) {
                statsPerColor.get(color).put(statistic,0.0);
            }
        }
    }

    public void initOverallStatistics(HashMap<Statistics.Statistic, Double> overallStatistics) {
        for(Statistics.Statistic statistic : Statistics.Statistic.values()) {
            overallStatistics.put(statistic, 0.0);
        }
    }



    //
    public void calcStatisticsPerColor() {

        try {

            //fetch lutemons by color
            HashMap<Lutemon.Color, ArrayList<Lutemon>> lutemonsByColor = Storage.getInstance().getLutemonsByColor();

            for (Lutemon.Color color : Lutemon.Color.values()) {
                for (Lutemon lm : lutemonsByColor.get(color)) {
                    //COUNT
                    statisticsPerColor.get(color).put(Statistics.Statistic.COUNT,
                            (double) lutemonsByColor.get(color).size()
                    );

                    //EXP_BY_FIGHTING
                    statisticsPerColor.get(color).put(Statistic.EXP_BY_FIGHTING,
                            statisticsPerColor.get(color).get(Statistic.EXP_BY_FIGHTING) + (double) lm.getAmountOfExperienceFought()
                    );

                    //EXP_BY_TRAINING
                    statisticsPerColor.get(color).put(Statistic.EXP_BY_TRAINING,
                            statisticsPerColor.get(color).get(Statistic.EXP_BY_TRAINING) + (double) lm.getAmountOfExperienceTrained()
                    );

                    //EXP
                    statisticsPerColor.get(color).put(Statistic.EXP,
                            statisticsPerColor.get(color).get(Statistic.EXP) + (double) lm.getAmountOfExperienceFought() + (double) lm.getAmountOfExperienceTrained()
                    );

                    //NO_ROUNDS
                    statisticsPerColor.get(color).put(Statistic.NO_ROUNDS,
                            statisticsPerColor.get(color).get(Statistic.NO_ROUNDS) + (double) lm.getNoRoundsFought()
                    );

                    //NO_LOSSES
                    statisticsPerColor.get(color).put(Statistic.NO_LOSSES,
                            statisticsPerColor.get(color).get(Statistic.NO_LOSSES) + (double) lm.getNoLosses()
                    );

                    //NO_WINS
                    statisticsPerColor.get(color).put(Statistic.NO_WINS,
                            statisticsPerColor.get(color).get(Statistic.NO_WINS) + (double) lm.getNoWins()
                    );

                    //NO_MATCHES
                    statisticsPerColor.get(color).put(Statistic.NO_MATCHES,
                            statisticsPerColor.get(color).get(Statistic.NO_MATCHES) + (double) lm.getNoWins() + (double) lm.getNoLosses()
                    );

                    //NO_REVIVES
                    statisticsPerColor.get(color).put(Statistic.NO_REVIVES,
                            statisticsPerColor.get(color).get(Statistic.NO_REVIVES) + (double) lm.getNoTimesRevived()
                    );

                    //NO_TRAINED
                    statisticsPerColor.get(color).put(Statistic.NO_TRAINED,
                            statisticsPerColor.get(color).get(Statistic.NO_TRAINED) + (double) lm.getNoTrained()
                    );

                    //NO_ATTACKS
                    statisticsPerColor.get(color).put(Statistic.NO_ATTACKS,
                            statisticsPerColor.get(color).get(Statistic.NO_ATTACKS) + (double) lm.getNoAttacks()
                    );

                    //NO_DEFENSES
                    statisticsPerColor.get(color).put(Statistic.NO_DEFENCES,
                            statisticsPerColor.get(color).get(Statistic.NO_DEFENCES) + (double) lm.getNoDefences()
                    );

                    //NO_LEVELS
                    //not documented
                }

            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    public void calcOverallStatistics() {

        try {

            //private HashMap<Statistics.Statistic, Double> overallStatistics;
            for (Statistics.Statistic statistic : Statistics.Statistic.values()) {
                for (Lutemon.Color color : Lutemon.Color.values()) {

                    //COUNT
                    overallStatistics.put(Statistics.Statistic.COUNT,
                            overallStatistics.get(Statistics.Statistic.COUNT) +
                                    statisticsPerColor.get(color).get(Statistics.Statistic.COUNT)
                    );


                    //EXP_BY_FIGHTING
                    overallStatistics.put(Statistic.EXP_BY_FIGHTING,
                            overallStatistics.get(Statistic.EXP_BY_FIGHTING) +
                                    statisticsPerColor.get(color).get(Statistic.EXP_BY_FIGHTING)
                    );


                    //EXP_BY_TRAINING
                    overallStatistics.put(Statistic.EXP_BY_FIGHTING,
                            overallStatistics.get(Statistic.EXP_BY_FIGHTING) +
                                    statisticsPerColor.get(color).get(Statistic.EXP_BY_FIGHTING)
                    );

                    //EXP
                    overallStatistics.put(Statistic.EXP,
                            overallStatistics.get(Statistic.EXP) +
                                    statisticsPerColor.get(color).get(Statistic.EXP)
                    );

                    //NO_ROUNDS
                    overallStatistics.put(Statistic.NO_ROUNDS,
                            overallStatistics.get(Statistic.NO_ROUNDS) +
                                    statisticsPerColor.get(color).get(Statistic.NO_ROUNDS)
                    );

                    //NO_LOSSES
                    overallStatistics.put(Statistic.NO_LOSSES,
                            overallStatistics.get(Statistic.NO_LOSSES) +
                                    statisticsPerColor.get(color).get(Statistic.NO_LOSSES)
                    );

                    //NO_WINS
                    overallStatistics.put(Statistic.NO_WINS,
                            overallStatistics.get(Statistic.NO_WINS) +
                                    statisticsPerColor.get(color).get(Statistic.NO_WINS)
                    );

                    //NO_MATCHES
                    overallStatistics.put(Statistic.NO_MATCHES,
                            overallStatistics.get(Statistic.NO_MATCHES) +
                                    statisticsPerColor.get(color).get(Statistic.NO_MATCHES)
                    );

                    //NO_REVIVES
                    overallStatistics.put(Statistic.NO_REVIVES,
                            overallStatistics.get(Statistic.NO_REVIVES) +
                                    statisticsPerColor.get(color).get(Statistic.NO_REVIVES)
                    );

                    //NO_TRAINED
                    overallStatistics.put(Statistic.NO_TRAINED,
                            overallStatistics.get(Statistic.NO_TRAINED) +
                                    statisticsPerColor.get(color).get(Statistic.NO_TRAINED)
                    );

                    //NO_ATTACKS
                    overallStatistics.put(Statistic.NO_ATTACKS,
                            overallStatistics.get(Statistic.NO_ATTACKS) +
                                    statisticsPerColor.get(color).get(Statistic.NO_ATTACKS)
                    );

                    //NO_DEFENSES
                    overallStatistics.put(Statistic.NO_DEFENCES,
                            overallStatistics.get(Statistic.NO_DEFENCES) +
                                    statisticsPerColor.get(color).get(Statistic.NO_DEFENCES)
                    );

                    //NO_LEVELS
                    //not documented
                }
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}