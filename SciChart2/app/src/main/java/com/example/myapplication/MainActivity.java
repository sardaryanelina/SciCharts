package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.scichart.charting.model.AxisCollection;
import com.scichart.charting.model.ChartModifierCollection;
import com.scichart.charting.model.RenderableSeriesCollection;
import com.scichart.charting.model.dataSeries.XyDataSeries;
import com.scichart.charting.modifiers.PinchZoomModifier;
import com.scichart.charting.modifiers.ZoomExtentsModifier;
import com.scichart.charting.modifiers.ZoomPanModifier;
import com.scichart.charting.visuals.SciChartSurface;
import com.scichart.charting.visuals.axes.DateAxis;
import com.scichart.charting.visuals.axes.AxisAlignment;
import com.scichart.charting.visuals.axes.NumericAxis;
import com.scichart.charting.visuals.renderableSeries.FastLineRenderableSeries;
import com.scichart.core.model.DateValues;
import com.scichart.core.model.DoubleValues;
import com.scichart.core.utility.DateIntervalUtil;
import com.scichart.drawing.common.PenStyle;
import com.scichart.drawing.common.SolidPenStyle;
import com.scichart.drawing.utility.ColorUtil;
import com.scichart.extensions.builders.SciChartBuilder;

import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try {
            SciChartSurface.setRuntimeLicenseKey("AjwAa9aqxydbZVaZpbXNWGbhLeBas0aMDwd78Ri+waPMmWm59a+K7JXn+qg7atXfDQR5usQiMSIDRZuP1D0C0c7al1za3NumxrJAn+LY1ZR/zGZU6sJGwekRz++UAalRwnA1ChePYk4HqK/+Tk7STA4sOT60+m4sfsn2A+Tlmpy+HYZEvObrvdqV25iEAReH9evERjgnRst7dffEF/SxEV3eEYN+/RL8q4/MrIGh68vcSEqPHI39t8PgsLetz3zJti46wwqBTQc5ofSrxgLnYYXAXmC+yncw/+XVQ6OICaD7S6dW0VMCqA7d1vKF0EpaFGfJ2BFuNZDrsL2fwA52bNw52BnTr33cI98LWl49k8Hyn9dx/9J+z04AMbo5PNx4uIhZIqeAIOAcvaGP1JDRNVJuVWE3bQACwtkIZiI9anSzxWaoAdzmLzan1e03nw58OkrfOL6UqxwUgbIVXuArsaYvPPFqOQNc0VGCWchThZgDGzWP0cYYYqTqDyhy4Efn3cmXsnu+vDanabQZPjijPmGBP9P8OkJbcD5rQXq7h8uyykcFu5TsD9fT4IsqNi4NEE/5+e0aNR78");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SciChart", "Error when setting the license", e);
        }

        // Create a SciChartSurface
        SciChartSurface surface = new SciChartSurface(this);

        // Get a layout declared in "activity_main.xml" by id
        LinearLayout chartLayout = (LinearLayout) findViewById(R.id.chart_layout);

        // Add the SciChartSurface to the layout
        chartLayout.addView(surface);

        // Initialize the SciChartBuilder
        SciChartBuilder.init(this);

        // Obtain the SciChartBuilder instance
        final SciChartBuilder sciChartBuilder = SciChartBuilder.instance();

        //   Adding Axes to the SciChartSurface ------
        DateAxis xAxis = new DateAxis(this);

        AxisCollection xAxes = surface.getXAxes();
        xAxes.add(xAxis);

        // To set a title on the X axis, you have to call the setAxisTitle(…) method
        xAxis.setAxisTitle("X Axis");

        // Y axis can be added to the SciChartSurface:
        NumericAxis yAxis = new NumericAxis(this);

        AxisCollection yAxes = surface.getYAxes();
        yAxes.add(yAxis);

        // Lets configure the Y axis to appear at the left calling the setAxisAlignment(…) method:
        yAxis.setAxisAlignment(AxisAlignment.Left);

        // Using the abovementioned setAxisTitle(…), a title can be added to the Y axis:
        yAxis.setAxisTitle("Y Axis");


        //Adding RenerableSeries to the SciChartSurface ------
        // to make the SciChartSurface to draw a line we are going to use FastLineRenderableSeries in this tutorial.
        // Once the line series is created,
        // it can be added to the RenderableSeries collection of the SciChartSurface:
        FastLineRenderableSeries lineSeries = new FastLineRenderableSeries();

        RenderableSeriesCollection renderableSeries = surface.getRenderableSeries();

        renderableSeries.add(lineSeries);

        //To set a particular color and thickness to the line, you should call the setStrokeStyle(…) method.
        // It accepts a parameter of the PenStyle type. During the creation of a PenStyle instance,
        // you can pass the desired values as parameters to the constructor:
        PenStyle penStyle = new SolidPenStyle(ColorUtil.Green, true, 2f, null);

        // Now, we should tell the line series to use this PenStyle as StrokeStyle:
        lineSeries.setStrokeStyle(penStyle);


        // Adding a DataSeries to a RenderableSeries ------
        // The line series requires some data to be drawn.
        // For best performance, SciChart provides a bunch of special classes called “DataSeries”
        // which are designed to store data efficiently and access it in a rapid and optimal way.
        //The most usual DataSeries type is XyDataSeries, which is intended to store two collections, of X and Y values.
        // The constructor for a DataSeries requires a data type for every component to be passed in;
        // here, we will use Date for X and Double for Y values:
        XyDataSeries<Date, Double> dataSeries = new XyDataSeries<>(Date.class, Double.class);

        //To add data points to the DataSeries, you should call the append(…) method on it.
        //For this purpose, SciChart provides a special interface called IValues that allows to implement your own collections with fast access to data.
        //we are going to demonstrate how to use DoubleValues and DateValues.
        // First, you need to initialize instances, passing the desired capacity to the constructor (optionally):
        int size = 100;
        DateValues xValues = new DateValues(size);
        DoubleValues yValues = new DoubleValues(size);

        //Then, you should be able to add some X, Y data to the collections. For the simplicity’s sake,
        // lets use random generated doubles as Y values, and fill the collections in the following “for” loop:
        Random random = new Random();
        for (double i = 0; i < size; ++i) {

            long xValue = new Date().getTime() + DateIntervalUtil.fromDays(i);
            xValues.add(new Date(xValue));

            yValues.add(random.nextDouble());
        }

        // Now, the data values can be appended to the DataSeries:
        dataSeries.append(xValues, yValues);

        // Once this is done, you can assign the newly created DataSeries to the RenderableSeries:
        lineSeries.setDataSeries(dataSeries);

        // To make the SciChartSurface drawing the line series stretched to the extents of the data range, you should call the zoomExtents() method on it:
        surface.zoomExtents();


        // Adding Interaction to the SciChartSurface -----
        // For the tutorial’s sake, lets assume you want to allow users to zoom a chart in and out with a pinch gesture and scroll it with a finger.
        // The default PinchZoomModifier and ZoomPanModifier sounds like doing exactly what is required.
        // To add them to the SciChartSurface, you should do something pretty much similar to what we did before with axes and RenderableSeries:
        PinchZoomModifier zoomModifier = new PinchZoomModifier();
        ZoomPanModifier panModifier = new ZoomPanModifier();

        ChartModifierCollection chartModifiers = surface.getChartModifiers();

        chartModifiers.add(zoomModifier);
        chartModifiers.add(panModifier);

        // There is also a modifier, called “ZoomExtentsModifier”,
        // which allows you to zoom out to the extents of chart’s data instantaneously,
        // double-tapping a chart. Lets add it to the ChartModifierCollection too:
        ZoomExtentsModifier zoomExtentsModifier = new ZoomExtentsModifier();
        chartModifiers.add(zoomExtentsModifier);

        // Licensing the Application


    }
}