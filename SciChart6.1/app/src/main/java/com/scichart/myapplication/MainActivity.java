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

        // -------------------------  Code was used from Tutorial 05 - Adding Tooltips and Legends ---------------------------//

        try {
            SciChartSurface.setRuntimeLicenseKey("FF6zQ0JxELERIxQE4v6Spjpqm2/bui2dBjHjBNELSU0LdRcx0XUNWwr6ZB0fsJNN7EhMGZDd07VVZvwH6OSrR9R6mVxuZxYhdRdwl76USE1Tz2PWo1SWY3gxV3tg/t+p5RFsZL0jIMl3HR8EzSKtWQWPPyU7eQaNyRsCHCDfWvfAjx4kNLNW15o8BkBWmEv1G4cZarcIoCgLdzQv1CzdfY9UbF3d25Udg2ojjiAeMEgjpAvFlrEF/ECw3QG5Ygr4ZiWmqp75jsAWZ0cY/2Hw9FOOA+JNCuEo8G7+UXTrmcgbS+N37HTCCg0W4WAW1YdFxLSkHJeBqMxdz27DxA7kOd1ycExpp6QH8hs+Am3CDGZ9UNCeKk3/7pzq5kKLQo2ZsBGQiLnPLlFzkHL620Y0ezFDmdh2EuUpRl0wjHZRSebkhD//xe1iKEqX8ECM6DN4VgVe2MHEklyCQLGCnunfSlpef2ucNk9VYwDovap9l541/qhaEqdOZ2idcaaihHl8fQMGL85FtQa/i/YnAF2r");
        } catch (Exception e) {
            e.printStackTrace();
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
        // Create a numeric X axis
        final IAxis xAxis = sciChartBuilder.newNumericAxis()
                .withAxisTitle("X Axis Title")
                .withVisibleRange(-5, 15)
                .build();
        // Create a numeric Y axis
        final IAxis yAxis = sciChartBuilder.newNumericAxis()
                .withAxisTitle("Y Axis Title").withVisibleRange(0, 100).build();
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
        ModifierGroup chartModifiers = sciChartBuilder.newModifierGroup()
                .withPinchZoomModifier().withReceiveHandledEvents(true).build()
                .withZoomPanModifier().withReceiveHandledEvents(true).build()
                .build();
        // Add the Y axis to the YAxes collection of the surface
        Collections.addAll(surface.getYAxes(), yAxis);
        // Add the X axis to the XAxes collection of the surface
        Collections.addAll(surface.getXAxes(), xAxis);
        // Add the annotation to the Annotations collection of the surface
        Collections.addAll(surface.getAnnotations(), textAnnotation);
        // Add the interactions to the ChartModifiers collection of the surface
        Collections.addAll(surface.getChartModifiers(), chartModifiers);

         // Adding Series to the Chart -----
        // In SciChart, there are special classes called RenderableSeries
        // that are responsible for drawing different chart types, such as
        // lines (FastLineRenderableSeries),
        // columns (FastColumnsRenderableSeries),
        // candlestick series (FastCandlestickRenderableSeries),
        // filled area (FastMountainRenderableSeries),
        // heat maps (FastUniformHeatmapRenderableSeries),
        // error bars (FastErrorBarsRenderableSeries), etc.

        // In this tutorial, we are going to add a Line and a Scatter series onto the chart.
        //
        //First, we create some XY XyDataSeries data for both the line and scatter plots:

        // -------------------------  Tutorial 06.1 - Adding Realtime Updates  ---------------------------//

        // Adding New Data Values
        //We change the way we populate lineData and scatterData by spinning up a new thread and using java.util.
        // Timer to update it in the background.
        final XyDataSeries lineData = sciChartBuilder.newXyDataSeries(Integer.class, Double.class).build();
        final XyDataSeries scatterData = sciChartBuilder.newXyDataSeries(Integer.class, Double.class).build();
        // Add new data on update
        TimerTask updateDataTask = new TimerTask() {
            @Override
            public void run() {
                UpdateSuspender.using(surface, new Runnable() {
                    @Override
                    public void run() {
                        int x = lineData.getCount();
                        lineData.append(x, Math.sin(x * 0.1));
                        scatterData.append(x, Math.cos(x * 0.1));
                        // Zoom series to fit the viewport
                        surface.zoomExtents();
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
                .withPointMarker(pointMarker)
                .build();

        // Then add it to the surface:
        surface.getRenderableSeries().add(scatterSeries);

        // Zooming the Chart to the Data Extents
        // At this point our RenderableSeries is ready to be rendered.
        // Only one detail remains.
        //We need to bring the RenderableSeries to the Viewport of the SciChartSurface.
        // To do this, you can either set VisibleRanges on the axes manually or
        // call the zoomExtents() method on the SciChartSurface:
        surface.zoomExtents();

        // Note that the zoomExtents() method should always be called in the end of chart set up.
        // This will prevent VisibleRanges on axes from being overridden after the call to zoomExtents().

        // In SciChart, chart interactions are defined by ChartModifiers.
        // In addition to the SciChart modifiers you can write custom modifiers or extends existing ones.

        //The provided modifiers include PinchZoomModifierRubberBandXyZoomModifierZoomPanModifierZoomExtentsModifierCursorModifierRolloverModifier,
        // SeriesSelectionModifier and others.


        //Adding Chart Modifiers -----
        //Now we are going to create and configure a couple of new modifiers using SciChartBuilder and
        // add them to the ChartModifiers collection of the SciChartSurface:

        chartModifiers = sciChartBuilder.newModifierGroup()
                .withPinchZoomModifier().build()
                .withZoomPanModifier().withReceiveHandledEvents(true).build()
                .withZoomExtentsModifier().withReceiveHandledEvents(true).build()
                .withXAxisDragModifier().withReceiveHandledEvents(true).withDragMode(AxisDragModifierBase.AxisDragMode.Scale).withClipModeX(ClipMode.None).build()
                .withYAxisDragModifier().withReceiveHandledEvents(true).withDragMode(AxisDragModifierBase.AxisDragMode.Pan).build()
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

        // Add the LegendModifier to the SciChartSurface surface.getChartModifiers().add(legendModifier);


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
    }
}