package com.jonathan.proyectofinal.data;

import java.util.List;

public class Mensage {
    private String app_id;//, template_id;
    private List<String> included_segments;
    private MensagesContent contents;
    private MensagesContent headings;
    private List<String> include_player_ids;

    public String getApp_id() { return app_id; }
    public void setApp_id(String app_id) { this.app_id = app_id; }

    public List<String> getIncluded_segments() { return included_segments; }
    public void setIncluded_segments(List<String> included_segments) { this.included_segments = included_segments; }

    public MensagesContent getContents() { return contents; }
    public void setContents(MensagesContent contents) { this.contents = contents; }

    public MensagesContent getHeadings() { return headings; }
    public void setHeadings(MensagesContent headings) { this.headings = headings; }

    //public String getTemplate_id() { return template_id; }
    //public void setTemplate_id(String template_id) { this.template_id = template_id; }

    public List<String> getInclude_player_ids() { return include_player_ids; }
    public void setInclude_player_ids(List<String> include_player_ids) { this.include_player_ids = include_player_ids; }
}
