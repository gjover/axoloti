package axoloti.objectviews;

import axoloti.MainFrame;
import axoloti.PatchViewSwing;
import axoloti.Theme;
import axoloti.attribute.AttributeInstance;
import axoloti.attribute.AttributeInstanceController;
import axoloti.attributeviews.AttributeInstanceView;
import axoloti.attributeviews.AttributeInstanceViewFactory;
import axoloti.attributeviews.IAttributeInstanceView;
import axoloti.displays.DisplayInstance;
import axoloti.displays.DisplayInstanceController;
import axoloti.displayviews.DisplayInstanceView;
import axoloti.displayviews.DisplayInstanceViewFactory;
import axoloti.displayviews.IDisplayInstanceView;
import axoloti.inlets.IInletInstanceView;
import axoloti.inlets.InletInstance;
import axoloti.inlets.InletInstanceController;
import axoloti.inlets.InletInstanceView;
import axoloti.inlets.InletInstanceViewFactory;
import axoloti.mvc.AbstractController;
import axoloti.mvc.array.ArrayModel;
import axoloti.mvc.array.ArrayView;
import axoloti.object.AxoObject;
import axoloti.object.AxoObjectFromPatch;
import axoloti.object.AxoObjectInstance;
import axoloti.object.ObjectInstanceController;
import axoloti.outlets.IOutletInstanceView;
import axoloti.outlets.OutletInstance;
import axoloti.outlets.OutletInstanceController;
import axoloti.outlets.OutletInstanceView;
import axoloti.outlets.OutletInstanceViewFactory;
import axoloti.parameters.ParameterInstance;
import axoloti.parameters.ParameterInstanceController;
import axoloti.parameterviews.IParameterInstanceView;
import axoloti.parameterviews.ParameterInstanceView;
import axoloti.parameterviews.ParameterInstanceViewFactory;
import components.LabelComponent;
import components.PopupIcon;
import java.awt.Component;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class AxoObjectInstanceView extends AxoObjectInstanceViewAbstract implements IAxoObjectInstanceView {

    LabelComponent IndexLabel;
    public final JPanel p_parameterViews = new JPanel();
    public final JPanel p_displayViews = new JPanel();
    public final JPanel p_ioletViews = new JPanel();
    public final JPanel p_inletViews = new JPanel();
    public final JPanel p_outletViews = new JPanel();

    public AxoObjectInstanceView(AxoObjectInstance model, ObjectInstanceController controller, PatchViewSwing patchView) {
        super(model, controller, patchView);
        init1();
    }

    @Override
    public AxoObjectInstance getModel() {
        return (AxoObjectInstance)model;
    }
    
    public AxoObject getType() {
        return getModel().getType();
    }

    final void init1() {
        p_ioletViews.setBackground(Theme.getCurrentTheme().Object_Default_Background);
        p_ioletViews.setLayout(new BoxLayout(p_ioletViews, BoxLayout.LINE_AXIS));
        p_ioletViews.setAlignmentX(LEFT_ALIGNMENT);
        p_ioletViews.setAlignmentY(TOP_ALIGNMENT);

        p_inletViews.setBackground(Theme.getCurrentTheme().Object_Default_Background);
        p_inletViews.setLayout(new BoxLayout(p_inletViews, BoxLayout.PAGE_AXIS));
        p_inletViews.setAlignmentX(LEFT_ALIGNMENT);
        p_inletViews.setAlignmentY(TOP_ALIGNMENT);

        p_outletViews.setBackground(Theme.getCurrentTheme().Object_Default_Background);
        p_outletViews.setLayout(new BoxLayout(p_outletViews, BoxLayout.PAGE_AXIS));
        p_outletViews.setAlignmentX(RIGHT_ALIGNMENT);
        p_outletViews.setAlignmentY(TOP_ALIGNMENT);

        p_parameterViews.setBackground(Theme.getCurrentTheme().Object_Default_Background);
        p_parameterViews.setAlignmentX(LEFT_ALIGNMENT);

        p_displayViews.setBackground(Theme.getCurrentTheme().Object_Default_Background);
        p_displayViews.setAlignmentX(LEFT_ALIGNMENT);
    }

    public void clear() {
        p_parameterViews.removeAll();
        p_displayViews.removeAll();
        p_ioletViews.removeAll();
        p_inletViews.removeAll();
        p_outletViews.removeAll();
//      inletInstanceViews.clear();
//      outletInstanceViews.clear();
//      parameterInstanceViews.clear();
    }

    ArrayView<IInletInstanceView> inletInstanceViews;
    ArrayView<IOutletInstanceView> outletInstanceViews;
    ArrayView<IAttributeInstanceView> attributeInstanceViews;
    ArrayView<IParameterInstanceView> parameterInstanceViews;
    ArrayView<IDisplayInstanceView> displayInstanceViews;

    @Override
    public void PostConstructor() {
        super.PostConstructor();
        clear();
        model.updateObj1();

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        final PopupIcon popupIcon = new PopupIcon();
        popupIcon.setPopupIconListener(new PopupIcon.PopupIconListener() {
            @Override
            public void ShowPopup() {
                JPopupMenu popup = CreatePopupMenu();
                popupIcon.add(popup);
                popup.show(popupIcon,
                        0, popupIcon.getHeight());
            }
        });
        Titlebar.add(popupIcon);

        LabelComponent idlbl = new LabelComponent(model.typeName);
        idlbl.setAlignmentX(LEFT_ALIGNMENT);
        idlbl.setForeground(Theme.getCurrentTheme().Object_TitleBar_Foreground);
        Titlebar.add(idlbl);

        String tooltiptxt = "<html>";
        if ((getType().sDescription != null) && (!getType().sDescription.isEmpty())) {
            tooltiptxt += getType().sDescription;
        }
        if ((getType().sAuthor != null) && (!getType().sAuthor.isEmpty())) {
            tooltiptxt += "<p>Author: " + getType().sAuthor;
        }
        if ((getType().sLicense != null) && (!getType().sLicense.isEmpty())) {
            tooltiptxt += "<p>License: " + getType().sLicense;
        }
        if ((getType().sPath != null) && (!getType().sPath.isEmpty())) {
            tooltiptxt += "<p>Path: " + getType().sPath;
        }
        Titlebar.setToolTipText(tooltiptxt);

        /*
         h.add(Box.createHorizontalStrut(3));
         h.add(Box.createHorizontalGlue());
         h.add(new JSeparator(SwingConstants.VERTICAL));*/
        ////IndexLabel not shown, maybe useful later...
        //IndexLabel.setSize(IndexLabel.getMinimumSize());
        //IndexLabel = new LabelComponent("");
        //refreshIndex();
        //h.add(IndexLabel);
        //IndexLabel.setAlignmentX(RIGHT_ALIGNMENT);
        Titlebar.setAlignmentX(LEFT_ALIGNMENT);
        add(Titlebar);
        InstanceLabel = new LabelComponent(model.getInstanceName());
        InstanceLabel.setAlignmentX(LEFT_ALIGNMENT);
        InstanceLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    addInstanceNameEditor();
                    e.consume();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        add(InstanceLabel);

        p_ioletViews.setBackground(Theme.getCurrentTheme().Object_Default_Background);

        p_ioletViews.setLayout(new BoxLayout(p_ioletViews, BoxLayout.LINE_AXIS));
        p_ioletViews.setAlignmentX(LEFT_ALIGNMENT);
        p_ioletViews.setAlignmentY(TOP_ALIGNMENT);
        p_inletViews.setBackground(Theme.getCurrentTheme().Object_Default_Background);

        p_inletViews.setLayout(new BoxLayout(p_inletViews, BoxLayout.PAGE_AXIS));
        p_inletViews.setAlignmentX(LEFT_ALIGNMENT);
        p_inletViews.setAlignmentY(TOP_ALIGNMENT);
        p_outletViews.setBackground(Theme.getCurrentTheme().Object_Default_Background);

        p_outletViews.setLayout(new BoxLayout(p_outletViews, BoxLayout.PAGE_AXIS));
        p_outletViews.setAlignmentX(RIGHT_ALIGNMENT);
        p_outletViews.setAlignmentY(TOP_ALIGNMENT);
        p_parameterViews.setBackground(Theme.getCurrentTheme().Object_Default_Background);
        if (getType().getRotatedParams()) {
            p_parameterViews.setLayout(new BoxLayout(p_parameterViews, BoxLayout.LINE_AXIS));
        } else {
            p_parameterViews.setLayout(new BoxLayout(p_parameterViews, BoxLayout.PAGE_AXIS));
        }
        p_displayViews.setBackground(Theme.getCurrentTheme().Object_Default_Background);

        if (getType().getRotatedParams()) {
            p_displayViews.setLayout(new BoxLayout(p_displayViews, BoxLayout.LINE_AXIS));
        } else {
            p_displayViews.setLayout(new BoxLayout(p_displayViews, BoxLayout.PAGE_AXIS));
        }
        p_displayViews.add(Box.createHorizontalGlue());
        p_parameterViews.add(Box.createHorizontalGlue());
/*
        for (Inlet inlet : getType().inlets) {
            InletInstance inletInstanceP = null;
            for (InletInstance inletInstance : pInletInstances) {
                if (inletInstance.GetLabel().equals(inlet.getName())) {
                    inletInstanceP = inletInstance;
                }
            }
            InletInstance inletInstance = new InletInstance(inlet, getObjectInstance());
            if (inletInstanceP != null) {
                Net n = getPatchModel().GetNet(inletInstanceP);
                if (n != null) {
                    n.connectInlet(inletInstance);
                }
            }
            getModel().inletInstances.add(inletInstance);
            //InletInstanceView view = (InletInstanceView) inletInstance.viewFactory(this);
            InletInstanceController c = inletInstance.createController(getController().getDocumentRoot());
            InletInstanceView view = InletInstanceViewFactory.viewFactory(c, this);
            view.setAlignmentX(LEFT_ALIGNMENT);
            p_inletViews.add(view);
            inletInstanceViews.add(view);
        }
        // disconnect stale inlets from nets
        for (InletInstance inletInstance : pInletInstances) {
            getPatchModel().disconnect(inletInstance);
        }
*/
        
        inletInstanceViews = new ArrayView<IInletInstanceView>(controller.inletInstanceControllers) {
            @Override
            public InletInstanceView viewFactory(AbstractController ctrl) {
                return InletInstanceViewFactory.createView((InletInstanceController)ctrl, AxoObjectInstanceView.this);
            }

            @Override
            public void updateUI() {
            }
        };
        controller.inletInstanceControllers.addView(inletInstanceViews);
        for (IInletInstanceView view : inletInstanceViews){
            view.setAlignmentX(LEFT_ALIGNMENT);
            p_inletViews.add((Component)view);
        }
        
/*        
        for (Outlet o : getType().outlets) {
            OutletInstance outletInstanceP = null;
            for (OutletInstance outletInstance : pOutletInstances) {
                if (outletInstance.GetLabel().equals(o.getName())) {
                    outletInstanceP = outletInstance;
                }
            }
            OutletInstance outletInstance = new OutletInstance(o, getObjectInstance());
            if (outletInstanceP != null) {
                Net n = getPatchModel().GetNet(outletInstanceP);
                if (n != null) {
                    n.connectOutlet(outletInstance);
                }
            }
            // need a view here
            getModel().outletInstances.add(outletInstance);
            OutletInstanceController c = outletInstance.createController(getController().getDocumentRoot());
            OutletInstanceView view = OutletInstanceViewFactory.viewFactory(c, this);
//            OutletInstanceView view = (OutletInstanceView) outletInstance.viewFactory(this);
            view.setAlignmentX(RIGHT_ALIGNMENT);
            p_outletViews.add(view);
            outletInstanceViews.add(view);
        }
        // disconnect stale outlets from nets
        for (OutletInstance outletInstance : pOutletInstances) {
            getPatchModel().disconnect(outletInstance);
        }

*/
        
        outletInstanceViews = new ArrayView<IOutletInstanceView>(controller.outletInstanceControllers) {
            @Override
            public OutletInstanceView viewFactory(AbstractController ctrl) {
                return OutletInstanceViewFactory.createView((OutletInstanceController)ctrl, AxoObjectInstanceView.this);
            }
            @Override
            public void updateUI() {
            }
        };
        controller.outletInstanceControllers.addView(outletInstanceViews);
        for (IOutletInstanceView view : outletInstanceViews){
            view.setAlignmentX(RIGHT_ALIGNMENT);
            p_outletViews.add((Component)view);
        }

        p_ioletViews.add(p_inletViews);
        p_ioletViews.add(Box.createHorizontalGlue());
        p_ioletViews.add(p_outletViews);
        add(p_ioletViews);
        
/*        
        for (AxoAttribute p : getType().attributes) {
            AttributeInstance attributeInstanceP = null;
            for (AttributeInstance attributeInstance : pAttributeInstances) {
                if (attributeInstance.getAttributeName().equals(p.getName())) {
                    attributeInstanceP = attributeInstance;
                }
            }
            AttributeInstance attributeInstance1 = p.CreateInstance(getObjectInstance(), attributeInstanceP);
            AttributeInstanceController c = attributeInstance1.createController(getController().getDocumentRoot());
            AttributeInstanceView attributeInstanceView = AttributeInstanceViewFactory.viewFactory(c, this);
            attributeInstanceView.setAlignmentX(LEFT_ALIGNMENT);
            addAttributeInstanceView(attributeInstanceView);
            attributeInstanceView.doLayout();
            getModel().attributeInstances.add(attributeInstance1);
        }
*/
        
        attributeInstanceViews = new ArrayView<IAttributeInstanceView>(controller.attributeInstanceControllers) {
            @Override
            public AttributeInstanceView viewFactory(AbstractController ctrl) {
                return AttributeInstanceViewFactory.createView((AttributeInstanceController)ctrl, AxoObjectInstanceView.this);
            }
            @Override
            public void updateUI() {
            }
        };
        controller.attributeInstanceControllers.addView(attributeInstanceViews);
        for (IAttributeInstanceView view : attributeInstanceViews){
            addAttributeInstanceView(view);
        }
        
/*
        for (Parameter p : getType().params) {
            ParameterInstance pin = p.CreateInstance(getObjectInstance());
            for (ParameterInstance pinp : pParameterInstances) {
                if (pinp.getName().equals(pin.getName())) {
                    pin.CopyValueFrom(pinp);
                }
            }
            ParameterInstanceController c = pin.createController(getController().getDocumentRoot());
            ParameterInstanceView view = ParameterInstanceViewFactory.viewFactory(c, this);
            addParameterInstanceView(view);
            view.setAlignmentX(RIGHT_ALIGNMENT);
            getModel().parameterInstances.add(pin);
        }
*/
        parameterInstanceViews = new ArrayView<IParameterInstanceView>(controller.parameterInstanceControllers) {
            @Override
            public IParameterInstanceView viewFactory(AbstractController ctrl) {
                return ParameterInstanceViewFactory.createView((ParameterInstanceController)ctrl, AxoObjectInstanceView.this);
            }
            @Override
            public void updateUI() {
            }
        };

        controller.parameterInstanceControllers.addView(parameterInstanceViews);
        for (IParameterInstanceView v : parameterInstanceViews){
            addParameterInstanceView(v);
        }
/*
        for (Display p : getType().displays) {
            DisplayInstance pin = p.CreateInstance(getObjectInstance());
            DisplayInstanceController c = pin.createController(getController().getDocumentRoot());
            DisplayInstanceView view = DisplayInstanceView.createView(c, null);
            addDisplayInstanceView(view);
//                    (DisplayInstanceView) pin.viewFactory(this);
            view.setAlignmentX(RIGHT_ALIGNMENT);
            view.doLayout();
            getModel().displayInstances.add(pin);
        }
//        p_displays.add(Box.createHorizontalGlue());
//        p_params.add(Box.createHorizontalGlue());
*/        
        
        displayInstanceViews = new ArrayView<IDisplayInstanceView>(controller.displayInstanceControllers) {
            @Override
            public IDisplayInstanceView viewFactory(AbstractController ctrl) {
                return DisplayInstanceViewFactory.createView((DisplayInstanceController)ctrl, AxoObjectInstanceView.this);
            }
            @Override
            public void updateUI() {
            }
        };

        controller.displayInstanceControllers.addView(displayInstanceViews);
        for (IDisplayInstanceView v : displayInstanceViews){
            addDisplayInstanceView(v);
        }
        
        
        add(p_parameterViews);
        add(p_displayViews);
        p_parameterViews.setAlignmentX(LEFT_ALIGNMENT);
        p_displayViews.setAlignmentX(LEFT_ALIGNMENT);

        synchronized (getTreeLock()) {
            validateTree();
        }
        setLocation(model.getX(), model.getY());
        resizeToGrid();
    }

    @Override
    JPopupMenu CreatePopupMenu() {
        JPopupMenu popup = super.CreatePopupMenu();
        JMenuItem popm_edit = new JMenuItem("edit object definition");
        popm_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                OpenEditor();
            }
        });
        popup.add(popm_edit);
        JMenuItem popm_editInstanceName = new JMenuItem("edit instance name");
        popm_editInstanceName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                addInstanceNameEditor();
            }
        });
        popup.add(popm_editInstanceName);
        JMenuItem popm_substitute = new JMenuItem("replace");
        popm_substitute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                getPatchView().ShowClassSelector(AxoObjectInstanceView.this.getLocation(), AxoObjectInstanceView.this, null);
            }
        });
        popup.add(popm_substitute);
        if (getType().GetHelpPatchFile() != null) {
            JMenuItem popm_help = new JMenuItem("help");
            popm_help.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    PatchViewSwing.OpenPatch(getType().GetHelpPatchFile());
                }
            });
            popup.add(popm_help);
        }
        if (MainFrame.prefs.getExpertMode()) {
            JMenuItem popm_adapt = new JMenuItem("adapt homonym");
            popm_adapt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    model.PromoteToOverloadedObj();
                }
            });
            popup.add(popm_adapt);
        }

        if (model.getType() instanceof AxoObjectFromPatch) {
            JMenuItem popm_embed = new JMenuItem("embed as patch/patcher");
            popm_embed.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (!getPatchView().isLocked()) {
                        getModel().ConvertToPatchPatcher();
                    }
                }
            });
            popup.add(popm_embed);
        } else if (!(this instanceof AxoObjectInstanceViewPatcherObject)) {
            JMenuItem popm_embed = new JMenuItem("embed as patch/object");
            popm_embed.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (!getPatchView().isLocked()) {
                        getModel().ConvertToEmbeddedObj();
                    }
                }
            });
            popup.add(popm_embed);
        }
        return popup;
    }

    public void refreshIndex() {
        if (getPatchView() != null && IndexLabel != null) {
            IndexLabel.setText(" " + getPatchView().getObjectInstanceViews().getSubViews().indexOf(this));
        }
    }

    public void OpenEditor() {
        getType().OpenEditor(getModel().editorBounds, getModel().editorActiveTabIndex);
    }

    @Override
    public void Lock() {
        super.Lock();
        for (IAttributeInstanceView a : attributeInstanceViews) {
            a.Lock();
        }
    }

    @Override
    public void Unlock() {
        super.Unlock();
        for (IAttributeInstanceView a : attributeInstanceViews) {
            a.UnLock();
        }
        if (getModel().deferredObjTypeUpdate) {
            getModel().updateObj();
            getModel().deferredObjTypeUpdate = false;
        }
    }

    @Override
    public AxoObjectInstance getObjectInstance() {
        return getModel();
    }

    @Override
    public ArrayView<IInletInstanceView> getInletInstanceViews() {
        return inletInstanceViews;
    }

    @Override
    public ArrayView<IOutletInstanceView> getOutletInstanceViews() {
        return outletInstanceViews;
    }

    @Override
    public ArrayView<IParameterInstanceView> getParameterInstanceViews() {
        return parameterInstanceViews;
    }

    @Override
    public void addParameterInstanceView(IParameterInstanceView view) {
        p_parameterViews.add((ParameterInstanceView) view);
    }

    @Override
    public void addAttributeInstanceView(IAttributeInstanceView view) {
        add((AttributeInstanceView) view);
    }

    @Override
    public void addDisplayInstanceView(IDisplayInstanceView view) {
        p_displayViews.add((DisplayInstanceView) view);
    }

    @Override
    public void addOutletInstanceView(IOutletInstanceView view) {
        p_outletViews.add((OutletInstanceView) view);

    }

    @Override
    public void addInletInstanceView(IInletInstanceView view) {
        p_inletViews.add((InletInstanceView) view);
    }
}