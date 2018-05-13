package com.audiance.tvprediction.view;

import org.zkoss.chart.AxisLabels;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Legend;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import com.audiance.tvprediction.viewmodel.ChartViewModel;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class OldAudianceChaineView extends SelectorComposer<Window> {

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
		// chart.setTheme(Theme.GRAY);
		AxisLabels xLabels = chart.getXAxis().getLabels();
		xLabels.setRotation(-45);
		// xLabels.setStyle("fontSize: '13px'; fontFamily: 'Verdana, sans-serif'");
		chart.getTooltip().setValueSuffix("%");
		chart.getPlotOptions().getBar().getDataLabels().setEnabled(true);
		// chart.getPlotOptions().getBar().setStacking("normal");
		Legend legend = chart.getLegend();
		legend.setLayout("vertical");
		legend.setAlign("right");
		legend.setVerticalAlign("middle");
		legend.setBorderWidth(0);
		chart.getCredits().setEnabled(false);
		chart.getPlotOptions().getBar().setBorderWidth(5);

	}
}
