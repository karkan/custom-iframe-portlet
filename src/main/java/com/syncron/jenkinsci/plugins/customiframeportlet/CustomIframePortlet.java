package com.syncron.jenkinsci.plugins.customiframeportlet;

import hudson.Extension;
import hudson.model.Descriptor;
import hudson.model.Job;
import hudson.plugins.view.dashboard.DashboardPortlet;

import java.util.Iterator;

import org.kohsuke.stapler.DataBoundConstructor;

public class CustomIframePortlet extends DashboardPortlet {

	private String urlToBeFetched;
	private String effectiveUrl;
	private String divStyle;

	@DataBoundConstructor
	public CustomIframePortlet(String name, String urlToBeFetched, String divStyle) {
		super(name);
		this.setUrlToBeFetched(urlToBeFetched);
		this.divStyle = divStyle;
	}

	public String getUrlToBeFetched() {
		return urlToBeFetched;
	}

	public String getEffectiveUrl() {
		return effectiveUrl;
	}

	public String getDivStyle() {
		return divStyle;
	}

	public void setUrlToBeFetched(String urlToBeFetched) {
		this.urlToBeFetched = urlToBeFetched;
		this.overridePlaceholdersInUrl();
	}

	private void overridePlaceholdersInUrl() {
		if (urlToBeFetched != null) {
			effectiveUrl = urlToBeFetched.replaceAll("\\$\\{viewName\\}",
					getDashboard().getViewName());
			effectiveUrl = effectiveUrl.replaceAll("\\$\\{jobsList\\}",
					jobsListAsString());
		} else {
			effectiveUrl = null;
		}
	}

	private String jobsListAsString() {
		StringBuilder sb = new StringBuilder();
		Iterator<Job> jobs = getDashboard().getJobs().iterator();
		if (jobs.hasNext()) {
			sb.append(jobs.next().getName());
		}
		while (jobs.hasNext()) {
			sb.append(",");
			sb.append(jobs.next().getName());
		}
		return sb.toString();
	}

	@Extension
	public static class DescriptorImpl extends Descriptor<DashboardPortlet> {

		@Override
		public String getDisplayName() {
			return "Custom Iframe Portlet";
		}
	}

}
