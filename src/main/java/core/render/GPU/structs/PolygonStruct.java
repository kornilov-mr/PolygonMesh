package core.render.GPU.structs;

import org.jocl.struct.Struct;

public class PolygonStruct extends Struct {
    public VectorStruct pointStructA;
    public VectorStruct pointStructB;
    public VectorStruct pointStructC;
    public CoordinateFormStruct coordinateFrom;
    public int polygonColor;
    public int contourColor;


    public PolygonStruct(VectorStruct pointStructA, VectorStruct pointStructB, VectorStruct pointStructC, CoordinateFormStruct coordinateFrom, int polygonColor,int contourColor) {
        this.pointStructA = pointStructA;
        this.pointStructB = pointStructB;
        this.pointStructC = pointStructC;
        this.polygonColor = polygonColor;
        this.coordinateFrom = coordinateFrom;
        this.contourColor=contourColor;
    }
}
