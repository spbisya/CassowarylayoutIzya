package com.okunev.cassowarylayout;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 777 on 1/19/2016.
 */
public class ConstraintsResolver {
    String viewTag = null;
    HashMap<Integer, HashMap<String, String>> allConstraints = new HashMap<>();
    ArrayList<String> constrains = new ArrayList<>();
    int attr1, attr2, constant, multiplier, offset, relation, type;
    String from_object, to_object;
    Boolean height, width, x, y, x2, y2, CenterX, CenterY, top, right, left, bottom,
            margin_left, margin_right;

    public ConstraintsResolver(String viewTag, HashMap<Integer, HashMap<String, String>> allConstraints) {
        this.allConstraints = allConstraints;
        this.viewTag = viewTag;
    }


    public CharSequence[] resolve() {
        constrains = new ArrayList<>();
        for (int i = 0; i < allConstraints.size(); i++) {
            HashMap<String, String> constrainI = allConstraints.get(i);
            attr1 = Integer.parseInt(constrainI.get("attribute1"));
            attr2 = Integer.parseInt(constrainI.get("attribute2"));
            constant = Integer.parseInt(constrainI.get("constant"));
            from_object = constrainI.get("from_object");
            multiplier = Integer.parseInt(constrainI.get("multiplier"));
            offset = Integer.parseInt(constrainI.get("offset"));
            relation = Integer.parseInt(constrainI.get("relation"));
            to_object = constrainI.get("to_object");
            type = Integer.parseInt(constrainI.get("type"));
            typeResolver(type);
        }
        CharSequence[] cs = new CharSequence[]{"a"};
        cs = constrains.toArray(new CharSequence[constrains.size()]);
if(cs!=null)
        return cs;
        else
    return new CharSequence[]{"a"};
        //,"red1.height==25dp"
    }

    public void typeResolver(int type) {
        switch (type) {
            case 0:
                break;
            case 1:

                break;
            case 2:
                break;
            case 3:
                constrains.add(resolveAttributes(attr1, constant, true));
                break;
            case 4:
                constrains.add(viewTag + ".x == 0");
                constrains.add(viewTag + ".y == 0");
                constrains.add(viewTag + ".y2==container.height");
                constrains.add(viewTag + ".x2==container.width");
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                constrains.add(resolveAttributes(attr1, constant, true));
                break;
            case 8:
                break;
            case 9:
                break;
        }
    }

    public String resolveAttributes(int attr, int constant, boolean attr1) {
        switch (attr) {
            case 0:
                break;
            case 1:
                if (type == 7) {
                    return viewTag + ".left==" + constant + "dp";
                }
                break;
            case 2:
                if (type == 7) {
                    return viewTag + ".right==" + constant + "dp";
                }
                break;
            case 3:
                if (type == 7) {
                    return viewTag + ".top==" + constant + "dp";
                }
                break;
            case 4:
                if (type == 7) {
                    return viewTag + ".bottom==" + constant + "dp";
                }
                break;
            case 5:
                if (type == 7) {
                    return viewTag + ".margin_left==" + "parent.margin_left-" + constant + "dp";//+((View)v.getParent()).getWidth()
                }
                break;
            case 6:
                if (type == 7) {
                    return viewTag + ".margin_right==" + "parent.margin_right-" + constant + "dp";//+((View)v.getParent()).getWidth()
                }
                break;
            case 7:
                if (type > 0)
                    return viewTag + ".width==" + constant + "dp";//+((View)v.getParent()).getWidth()
                break;
            case 8:
                if (type > 0)
                    return viewTag + ".height==" + constant + "dp";
                break;
            case 9:
                if (type > 0)
                    if (type == 3)
                        return viewTag + ".centerX==parent.centerX";
                    else
                        return viewTag + ".centerX==" + constant + "dp";
                break;
            case 10:
                if (type > 0)
                    if (type == 3)
                        return viewTag + ".centerY==parent.centerY";
                    else
                        return viewTag + ".centerY==" + constant + "dp";
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
        }
        return null;
    }

}
