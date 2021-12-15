package com.scichart.myapplication;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.scichart.charting.ClipMode;
import com.scichart.charting.model.dataSeries.XyDataSeries;
import com.scichart.charting.modifiers.AxisDragModifierBase;
import com.scichart.charting.modifiers.ModifierGroup;
import com.scichart.charting.visuals.SciChartSurface;
import com.scichart.charting.visuals.annotations.HorizontalAnchorPoint;
import com.scichart.charting.visuals.annotations.TextAnnotation;
import com.scichart.charting.visuals.annotations.VerticalAnchorPoint;
import com.scichart.charting.visuals.axes.AxisAlignment;
import com.scichart.charting.visuals.axes.IAxis;
import com.scichart.charting.visuals.pointmarkers.EllipsePointMarker;
import com.scichart.charting.visuals.renderableSeries.IRenderableSeries;
import com.scichart.core.annotations.Orientation;
import com.scichart.core.framework.UpdateSuspender;
import com.scichart.drawing.utility.ColorUtil;
import com.scichart.extensions.builders.SciChartBuilder;

import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    // Create SciChartSurface using Builders
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // -------------- Tutorial 08 - Adding Multiple Axis -----------------//
        // SciChart supports unlimited axes.
        // This unlocks many possibilities , such as
        // rotated (vertical) charts,
        // mixed axes (have both the XAxis and YAxis on the left),
        // horizontally or vertically stacked axes, etc.
        // To illustrate, In this tutorial, we are going to add a second YAxis to the chart.
        // We are going to show how to register annotations and line series on the second axis.
        // We are also going to show how to ensure Axis drag behavior work on both axis.

        // Adding a Second Y Axis
        // The procedure to add a second axis to a SciChartSurface is pretty much the same as with one axis with one difference.
        // You must assign a unique string ID to all axes if there is more than one.
        //
        //To see the axis to appear to the either side of a chart, you set AxisAlignment to AxisAlignment.Left, AxisAlignment.Right, etc.

        // Let's add another axis and align it to the left side of the chart (assuming the original one is placed to the right).
        //
        //To have a different scale on the secondary axis, we are going to enlarge its VisibleRange by 40% setting a GrowBy value:


        // Registering RenderableSeries on an Axis
        // If there are several Y or X axes, you need to register other chart parts,
        // like RenderableSeries and Annotations, on a particular axis to be measured against its scale.
        //
        //From the tutorial, we are going to attach one series to the right axis and the other to the left axis,
        // passing corresponding IDs to the RenderableSeries:
        // Create and configure a line series

        // Create and configure a scatter series

        // Registering Annotations on an Axis
        // Annotations also need to be registered on a certain axis in a multi-axis scenario.
        // So we are going to assign the axis IDs to annotations in on our chart in this way:

        try {
            SciChartSurface.setRuntimeLicenseKey("FF6zQ0JxELERIxQE4v6Spjpqm2/bui2dBjHjBNELSU0LdRcx0XUNWwr6ZB0fsJNN7EhMGZDd07VVZvwH6OSrR9R6mVxuZxYhdRdwl76USE1Tz2PWo1SWY3gxV3tg/t+p5RFsZL0jIMl3HR8EzSKtWQWPPyU7eQaNyRsCHCDfWvfAjx4kNLNW15o8BkBWmEv1G4cZarcIoCgLdzQv1CzdfY9UbF3d25Udg2ojjiAeMEgjpAvFlrEF/ECw3QG5Ygr4ZiWmqp75jsAWZ0cY/2Hw9FOOA+JNCuEo8G7+UXTrmcgbS+N37HTCCg0W4WAW1YdFxLSkHJeBqMxdz27DxA7kOd1ycExpp6QH8hs+Am3CDGZ9UNCeKk3/7pzq5kKLQo2ZsBGQiLnPLlFzkHL620Y0ezFDmdh2EuUpRl0wjHZRSebkhD//xe1iKEqX8ECM6DN4VgVe2MHEklyCQLGCnunfSlpef2ucNk9VYwDovap9l541/qhaEqdOZ2idcaaihHl8fQMGL85FtQa/i/YnAF2r");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a SciChartSurface
        SciChartSurface surface = new SciChartSurface(this);

        // Get a layout declared in "activity_main.xml" by id
        LinearLayout chartLayout = findViewById(R.id.chart_layout);
        // Add the SciChartSurface to the layout
        chartLayout.addView(surface);
        // Initialize the SciChartBuilder
        SciChartBuilder.init(this);
        // Obtain the SciChartBuilder instance
        final SciChartBuilder sciChartBuilder = SciChartBuilder.instance();
        // Create a numeric X axis
        final IAxis xAxis = sciChartBuilder.newNumericAxis()
                .withAxisTitle("X Axis Title")
                .withVisibleRange(-5, 15)
                .build();
        // Create a numeric axis, right-aligned
        final IAxis yAxisRight = sciChartBuilder.newNumericAxis()
                .withAxisTitle("Primary")
                // Assign a unique ID to the axis
                .withAxisId("primaryYAxis")
                .withAxisAlignment(AxisAlignment.Right)
                .build();

        // Create another numeric axis, left-aligned
        final IAxis yAxisLeft = sciChartBuilder.newNumericAxis()
                .withAxisTitle("Secondary")
                // Assign a unique ID to the axis
                .withAxisId("secondaryYAxis")
                .withAxisAlignment(AxisAlignment.Left)
                .withGrowBy(0.2,0.2)
                .build();


        // Create a TextAnnotation and specify the inscription and position for it
        TextAnnotation textAnnotation = sciChartBuilder.newTextAnnotation()
                .withX1(5.0)
                .withY1(55.0)
                .withText("Hello World!")
                .withHorizontalAnchorPoint(HorizontalAnchorPoint.Center)
                .withVerticalAnchorPoint(VerticalAnchorPoint.Center)
                .withFontStyle(20, ColorUtil.White)
                .build();
        // Create interactivity modifiers

        //Adding Chart Modifiers -----
        //Now we are going to create and configure a couple of new modifiers using SciChartBuilder and
        // add them to the ChartModifiers collection of the SciChartSurface:

        ModifierGroup  chartModifiers = sciChartBuilder.newModifierGroup()
                .withPinchZoomModifier().build()
                .withZoomPanModifier().withReceiveHandledEvents(true).build()
                .withZoomExtentsModifier().withReceiveHandledEvents(true).build()
                .withXAxisDragModifier().withReceiveHandledEvents(true).withDragMode(AxisDragModifierBase.AxisDragMode.Scale).withClipModeX(ClipMode.None).build()
                .withYAxisDragModifier().withReceiveHandledEvents(true).withDragMode(AxisDragModifierBase.AxisDragMode.Pan).build()
                .build();

        final int fifoCapacity = 500;
        final XyDataSeries lineData = sciChartBuilder.newXyDataSeries(Integer.class, Double.class)
                .withFifoCapacity(fifoCapacity)
                .build();
        final XyDataSeries scatterData = sciChartBuilder.newXyDataSeries(Integer.class, Double.class)
                .withFifoCapacity(fifoCapacity)
                .build();
        TimerTask updateDataTask = new TimerTask() {
            private int x = 0;
            @Override
            public void run() {
                UpdateSuspender.using(surface, new Runnable() {
                    @Override
                    public void run() {
                        lineData.append(x, Math.sin(x * 0.1));
                        scatterData.append(x, Math.cos(x * 0.1));
                        // New code here
                        // Add an annotation every 100 data points
                        if(x%100 == 0) {
                            boolean isEven = x % 2 == 0;
                            String yAxisId = isEven ? "primaryYAxis" : "secondaryYAxis";

                            TextAnnotation marker = sciChartBuilder.newTextAnnotation()
                                    .withYAxisId(yAxisId)
                                    .withIsEditable(false)
                                    .withText("Test")
                                    .withBackgroundColor(ColorUtil.Green)
                                    .withX1(x)
                                    .withY1(0.0)
                                    .withVerticalAnchorPoint(VerticalAnchorPoint.Center)
                                    .withHorizontalAnchorPoint(HorizontalAnchorPoint.Center)
                                    .withFontStyle(20, ColorUtil.White)
                                    .withZIndex(1)
                                    .build();
                            surface.getAnnotations().add(marker);
                            // Remove one annotation from the beginning
                            // in the FIFO way
                            if(x > fifoCapacity){
                                surface.getAnnotations().remove(0);
                            }
                        }
                        // Zoom series to fit the viewport
                        surface.zoomExtents();
                        ++x;
                    }
                });
            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long interval = 10;
        timer.schedule(updateDataTask, delay, interval);

        //Now add DataSeries lineData to the RenderableSeries lineSeries:
        final IRenderableSeries lineSeries = sciChartBuilder.newLineSeries()
                .withDataSeries(lineData)
                // Register on a particular axis using its ID
                .withYAxisId("primaryYAxis")
                .withStrokeStyle(ColorUtil.LightBlue, 2f, true)
                .build();

        // Then add it to the surface:
        // Surface surface.getRenderableSeries().add(lineSeries);
        surface.getRenderableSeries().add(lineSeries);

        // Add a Scatter Plot -----
        // Similarly we can create a Scatter series. It requires a PointMarker instance.
        EllipsePointMarker pointMarker = sciChartBuilder
                .newPointMarker(new EllipsePointMarker())
                .withFill(ColorUtil.LightBlue)
                .withStroke(ColorUtil.Green, 2f)
                .withSize(10)
                .build();

        final IRenderableSeries scatterSeries = sciChartBuilder.newScatterSeries()
                .withDataSeries(scatterData)
                // Register on a particular axis using its ID
                .withYAxisId("secondaryYAxis")
                .withPointMarker(pointMarker)
                .build();

        // Then we add it to the collection:\
        Collections.addAll(surface.getChartModifiers(), chartModifiers);

        // Add a Legend
        //In SciChart, a chart legend can be created and configured via the LegendModifier:
        // Create a LegendModifier and configure a chart legend
        ModifierGroup legendModifier = sciChartBuilder.newModifierGroup()
                .withLegendModifier()
                .withOrientation(Orientation.HORIZONTAL)
                .withPosition(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 10)
                .build()
                .build();

        // Add a Cursor (Crosshair)
        //CursorModifier adds a crosshair onto a SciChartSurface.
        // When you put your finger on the screen or otherwise click it,
        // it shows series values in tooltips and chart coordinates at that point point.

        // Create and configure a CursorModifier
        ModifierGroup cursorModifier = sciChartBuilder.newModifierGroup()
                .withCursorModifier().withShowTooltip(true).build()
                .build();

        // Add the CursorModifier to the SciChartSurface
        surface.getChartModifiers().add(cursorModifier);

        // Add the chart modifiers to the SciChartSurface
        surface.getChartModifiers().add(chartModifiers);

        // Then add it to the surface:
        surface.getRenderableSeries().add(scatterSeries);

        // Add the LegendModifier to the SciChartSurface
        surface.getChartModifiers().add(legendModifier);

        // Add both Y axes to the YAxes collection of the surface
        Collections.addAll(surface.getYAxes(), yAxisLeft, yAxisRight);
        // Add the X axis to the XAxes collection of the surface
        Collections.addAll(surface.getXAxes(), xAxis);
        // Add the annotation to the Annotations collection of the surface
        Collections.addAll(surface.getAnnotations(), textAnnotation);
        // Add the interactions to the ChartModifiers collection of the surface
        Collections.addAll(surface.getChartModifiers(), chartModifiers);

        // Zooming the Chart to the Data Extents
        // At this point our RenderableSeries is ready to be rendered.
        // Only one detail remains.
        //We need to bring the RenderableSeries to the Viewport of the SciChartSurface.
        // To do this, you can either set VisibleRanges on the axes manually or
        // call the zoomExtents() method on the SciChartSurface:
        surface.zoomExtents();
    }
}