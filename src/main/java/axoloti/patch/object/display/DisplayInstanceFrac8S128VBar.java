/**
 * Copyright (C) 2013, 2014 Johannes Taelman
 *
 * This file is part of Axoloti.
 *
 * Axoloti is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * Axoloti is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Axoloti. If not, see <http://www.gnu.org/licenses/>.
 */
package axoloti.patch.object.display;

import axoloti.object.atom.AtomDefinitionController;
import axoloti.object.display.DisplayFrac8S128VBar;
import java.nio.ByteBuffer;

/**
 *
 * @author Johannes Taelman
 */
public class DisplayInstanceFrac8S128VBar extends DisplayInstance<DisplayFrac8S128VBar> {

    final int n = 128;

    DisplayInstanceFrac8S128VBar(AtomDefinitionController controller) {
        super(controller);
    }

    @Override
    public String GenerateCodeInit(String vprefix) {
        String s = "{\n"
                + "   int _i;\n"
                + "   for(_i=0;_i<" + n + ";_i++)\n"
                + "   " + GetCName() + "[_i] = 0;\n"
                + "}\n";
        return s;
    }

    @Override
    public String valueName(String vprefix) {
        return "(int8_t *)(&displayVector[" + offset + "])";
    }

    byte dst[] = new byte[n];
    int idst[] = new int[n];
    int[] value;

    @Override
    public void ProcessByteBuffer(ByteBuffer bb) {
        bb.get(dst);
        for (int i = 0; i < n; i++) {
            idst[i] = dst[i];
        }
        setValue(idst);
    }

    @Override
    public int getLength() {
        return n / 4;
    }

    public int[] getIDst() {
        return idst;
    }

    public int getN() {
        return n;
    }

    @Override
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = (int[]) value;
        firePropertyChange(DISP_VALUE, null, value);
    }
}