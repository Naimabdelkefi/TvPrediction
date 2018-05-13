package com.audiance.tvprediction.view;

import org.zkoss.chart.AxisLabels;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Legend;
import org.zkoss.chart.PlotLine;
import org.zkoss.chart.plotOptions.LinePlotOptions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import com.audiance.tvprediction.viewmodel.ChartViewModel;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class OldAudianceEmissionView extends SelectorComposer<Window> {

	@Wire
	Charts chart;

	@WireVariable
	ChartViewModel chartViewModel;

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);

		chart.setModel(chartViewModel.getCategoryModel());

		chart.getTitle().setX(-20);

		chart.getSubtitle().setX(-20);

		chart.getYAxis().setTitle("Ratio(%)");
		PlotLine plotLine = new PlotLine();
		plotLine.setValue(0);
		plotLine.setWidth(1);
		plotLine.setColor("#808080");
		chart.getYAxis().addPlotLine(plotLine);
		AxisLabels xLabels = chart.getXAxis().getLabels();
		xLabels.setRotation(-45);
		chart.getTooltip().setValueSuffix("%");
		chart.getPlotOptions().getBar().getDataLabels().setEnabled(true);
		Legend legend = chart.getLegend();
		legend.setLayout("vertical");
		legend.setAlign("right");
		legend.setVerticalAlign("middle");
		legend.setBorderWidth(0);
		chart.getCredits().setEnabled(false);
		LinePlotOptions linePlotOptions = chart.getPlotData().getPlotOptions().getLine();
		linePlotOptions.setEnableMouseTracking(false);
		linePlotOptions.getDataLabels().setEnabled(true);
	}
}
