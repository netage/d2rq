package de.fuberlin.wiwiss.d2rq.map;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import com.hp.hpl.jena.graph.Node;

import de.fuberlin.wiwiss.d2rq.rdql.NodeConstraint;

/**
 * NodeMaker that returns a fixed node.
 *
 * @author Richard Cyganiak (richard@cyganiak.de)
 * @version $Id: FixedNodeMaker.java,v 1.6 2006/08/30 10:25:26 cyganiak Exp $
 */
public class FixedNodeMaker implements NodeMaker {
	private Node fixedNode;

	public FixedNodeMaker(Node fixedNode) {
		this.fixedNode = fixedNode;
	}
	
    public void matchConstraint(NodeConstraint c) {
        c.matchFixedNode(fixedNode);
    }

	public boolean couldFit(Node node) {
		return Node.ANY.equals(node) || this.fixedNode.equals(node);
	}

	public Set getColumns() {
		return Collections.EMPTY_SET;
	}

	public Set getJoins() {
		return Collections.EMPTY_SET;
	}
	
	public Set getConditions() {
		return Collections.EMPTY_SET;
	}

	public AliasMap getAliases() {
		return AliasMap.NO_ALIASES;
	}
	
	public Map getColumnValues(Node node) {
		return Collections.EMPTY_MAP;
	}

	public Node getNode(String[] row, Map columnNameNumberMap) {
		return this.fixedNode;
	}
	
	public boolean isUnique() {
		return true;
	}
	
	public String toString() {
		return "Fixed(" + toString(this.fixedNode) + ")";
	}

	private static String toString(Node n) {
		if (n.isURI()) {
			return "<" + n.getURI() + ">";
		}
		if (n.isBlank()) {
			return "_:" + n.getBlankNodeLabel();
		}
		// Literal
		String s = "\"" + n.getLiteralLexicalForm() + "\"";
		if (!"".equals(n.getLiteralLanguage())) {
			s += "@" + n.getLiteralLanguage();
		}
		if (n.getLiteralDatatype() != null) {
			s += "^^<" + n.getLiteralDatatypeURI() + ">";
		}
		return s;
	}
}