package com.syncron.jenkinsci.plugins.customiframeportlet;

import hudson.Extension;
import hudson.model.Descriptor;
import hudson.plugins.view.dashboard.DashboardPortlet;

import org.kohsuke.stapler.DataBoundConstructor;

public class CustomIframePortlet extends DashboardPortlet {

	private String url;
	private String divStyle;

	@DataBoundConstructor
	public CustomIframePortlet(String name, String url, String divStyle) {
		super(name);
		this.url = url;
		this.divStyle = divStyle;
	}

	public String getUrl() {
		return url;
	}

	public String getDivStyle() {
		return divStyle;
	}

	@Extension
	public static class DescriptorImpl extends Descriptor<DashboardPortlet> {

		@Override
		public String getDisplayName() {
			return "Custom Iframe Portlet";
		}
	}

}
