/** Polygonal Planar Projection LIBrary (3plib) v0.1.0
 ** Copyright 2016 Frederic Viry
 ** author: Frederic Viry (Laboratoire Verimag, Grenoble, France)
 ** mail: ask3plib@gmail.com
 **
 ** This file is part of 3plib.
 **
 ** 3plib is free software: you can redistribute it and/or modify
 ** it under the terms of the GNU Lesser General Public License as published by
 ** the Free Software Foundation, either version 3 of the License, or
 ** at your option) any later version.
 ** 
 ** 3plib is distributed in the hope that it will be useful,
 ** but WITHOUT ANY WARRANTY; without even the implied warranty of
 ** MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 ** GNU Lesser General Public License for more details.
 ** 
 ** You should have received a copy of the GNU Lesser General Public License
 ** along with 3plib. If not, see <http://www.gnu.org/licenses/>.
 **/

import fr.imag.ppplib.calc.ProjectionCalculator;
import fr.imag.ppplib.calc.sf.ConvexPolyhedronSupportFunction;
import fr.imag.ppplib.calc.ImplementationFactory;
import fr.imag.ppplib.proj.plg.Polygon;
import fr.imag.ppplib.proj.PolygonalProjector;
import fr.imag.ppplib.proj.UnionPolygonalProjector;
import fr.imag.ppplib.proj.ErrorType;
import fr.imag.ppplib.io.gui.PolygonsViewer;

import java.util.List;
import java.util.ArrayList;

/** A test class for a chair.
 **/

public class TestChair
{
  public static void main(String[] args)
  {
    /* Chair construction */
    double[] dxp = new double[] {1.0, 0.0, 0.0};
    double[] dxm = new double[] {-1.0, 0.0, 0.0};
    double[] dyp = new double[] {0.0, 1.0, 0.0};
    double[] dym = new double[] {0.0, -1.0, 0.0};
    double[] dzp = new double[] {0.0, 0.0, 1.0};
    double[] dzm = new double[] {0.0, 0.0, -1.0};
    List <ConvexPolyhedronSupportFunction> chairParts = new ArrayList <ConvexPolyhedronSupportFunction> ();
    ConvexPolyhedronSupportFunction p1 = new ConvexPolyhedronSupportFunction();
    p1.addLinearConstraint(dxp, 2.0);
    p1.addLinearConstraint(dxm, -1.5);
    p1.addLinearConstraint(dyp, 2.0);
    p1.addLinearConstraint(dym, -1.5);
    p1.addLinearConstraint(dzp, -0.5);
    p1.addLinearConstraint(dzm, 2.0);
    ConvexPolyhedronSupportFunction p2 = new ConvexPolyhedronSupportFunction();
    p2.addLinearConstraint(dxp, -1.5);
    p2.addLinearConstraint(dxm, 2.0);
    p2.addLinearConstraint(dyp, 2.0);
    p2.addLinearConstraint(dym, -1.5);
    p2.addLinearConstraint(dzp, -0.5);
    p2.addLinearConstraint(dzm, 2.0);
    ConvexPolyhedronSupportFunction p3 = new ConvexPolyhedronSupportFunction();
    p3.addLinearConstraint(dxp, 2.0);
    p3.addLinearConstraint(dxm, -1.5);
    p3.addLinearConstraint(dyp, -1.5);
    p3.addLinearConstraint(dym, 2.0);
    p3.addLinearConstraint(dzp, -0.5);
    p3.addLinearConstraint(dzm, 2.0);
    ConvexPolyhedronSupportFunction p4 = new ConvexPolyhedronSupportFunction();
    p4.addLinearConstraint(dxp, -1.5);
    p4.addLinearConstraint(dxm, 2.0);
    p4.addLinearConstraint(dyp, -1.5);
    p4.addLinearConstraint(dym, 2.0);
    p4.addLinearConstraint(dzp, -0.5);
    p4.addLinearConstraint(dzm, 2.0);
    ConvexPolyhedronSupportFunction p5 = new ConvexPolyhedronSupportFunction();
    p5.addLinearConstraint(dxp, 2.0);
    p5.addLinearConstraint(dxm, 2.0);
    p5.addLinearConstraint(dyp, 2.0);
    p5.addLinearConstraint(dym, 2.0);
    p5.addLinearConstraint(dzp, 0.0);
    p5.addLinearConstraint(dzm, 0.5);
    ConvexPolyhedronSupportFunction p6 = new ConvexPolyhedronSupportFunction();
    p6.addLinearConstraint(dxp, 2.0);
    p6.addLinearConstraint(dxm, 2.0);
    p6.addLinearConstraint(dyp, 2.0);
    p6.addLinearConstraint(dym, -1.5);
    p6.addLinearConstraint(dzp, 2.0);
    p6.addLinearConstraint(dzm, 0.0);
    chairParts.add(p1);
    chairParts.add(p2);
    chairParts.add(p3);
    chairParts.add(p4);
    chairParts.add(p5);
    chairParts.add(p6);
 
    /* Error choice */
    double err = 0.0001;
 
    /* Plane choice */
    double[] v2 = new double[] {Math.sqrt(1.0/4.0), Math.sqrt(1.0/4.0), Math.sqrt(1.0/2.0)};
    double[] v1 = new double[] {Math.sqrt(2)/2.0, -Math.sqrt(2)/2.0, 0.0};
    ProjectionCalculator pc = ImplementationFactory.getNewProjectionCalculator(v1, v2);
 
    /* Projections calculus */
    PolygonalProjector pp = new UnionPolygonalProjector(true); 
    pp.computeProjection(chairParts, pc, ErrorType.RELATIVE, err);
 
    /* Get the result */
    List <Polygon> outerPoly = new ArrayList <Polygon>();
    outerPoly.addAll(pp.getOuterPolygons());
 
    /* View the result */
    PolygonsViewer pv = new PolygonsViewer();
    pv.addPolygons(outerPoly);
  }
}
