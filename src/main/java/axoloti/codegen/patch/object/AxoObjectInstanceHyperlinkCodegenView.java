
package axoloti.codegen.patch.object;

import axoloti.patch.object.AxoObjectInstanceHyperlink;
import axoloti.patch.object.IAxoObjectInstance;
import axoloti.patch.object.ObjectInstanceController;
import java.beans.PropertyChangeEvent;

/**
 *
 * @author jtaelman
 */
public class AxoObjectInstanceHyperlinkCodegenView implements IAxoObjectInstanceCodegenView {
    
    final ObjectInstanceController controller;
    
    public AxoObjectInstanceHyperlinkCodegenView(AxoObjectInstanceHyperlink model, ObjectInstanceController controller) {
        this.controller = controller;
    }

    @Override
    public IAxoObjectInstance getModel() {
        return controller.getModel();
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
    }

    @Override
    public ObjectInstanceController getController() {
        return controller;
    }

    @Override
    public String GenerateUICode(int[] count) {
        return "";
    }

    @Override
    public String GenerateInitCodePlusPlus(String vprefix, boolean enableOnParent) {
        return "";
    }

    @Override
    public String GenerateDisposeCodePlusPlus(String vprefix) {
        return "";
    }

    @Override
    public String GenerateClass(String ClassName, String OnParentAccess, Boolean enableOnParent) {
        return "";
    }

    @Override
    public String GenerateCallMidiHandler() {
        return "";
    }

    @Override
    public void dispose() {
    }

}