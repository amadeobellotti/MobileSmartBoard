package environment;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.Scanner;
import java.util.regex.Pattern;

import renderableObject.Ellipse;
import renderableObject.Line;
import renderableObject.Rectangle;
import renderableObject.RenderableObject;
import renderableObject.RenderableShape;
import renderableObject.Text;

public class XMLParser {
	public enum LegalTag {
		Illegal, Enviorment, Header, ProjectName, Name, Objects, Object, Type, Location, Line, Start, End, Thickness, Color, Font, Style, Size, Text
	}

	public XMLParser() {

	}

	public boolean isOpenTag(String tag) {
		return tag.startsWith("<") && !tag.startsWith("</");
	}

	public boolean isCloseTag(String tag) {
		return tag.startsWith("</");
	}

	public LegalTag parseTag(String tag) {
		tag = tag.trim();

		if (isOpenTag(tag))
			tag = tag.substring(1, tag.length() - 1);
		else
			tag = tag.substring(2, tag.length() - 1);

		switch (tag) {
		case "Enviorment":
			return LegalTag.Enviorment;
		case "Header":
			return LegalTag.Header;
		case "ProjectName":
			return LegalTag.ProjectName;
		case "Name":
			return LegalTag.Name;
		case "Objects":
			return LegalTag.Objects;
		case "Object":
			return LegalTag.Object;
		case "Type":
			return LegalTag.Type;
		case "Location":
			return LegalTag.Location;
		case "Line":
			return LegalTag.Line;
		case "Start":
			return LegalTag.Start;
		case "End":
			return LegalTag.End;
		case "Thickness":
			return LegalTag.Thickness;
		case "Color":
			return LegalTag.Color;
		case "Font":
			return LegalTag.Font;
		case "Style":
			return LegalTag.Style;
		case "Size":
			return LegalTag.Size;
		case "Text":
			return LegalTag.Text;
		default:
			return LegalTag.Illegal;

		}
	}

	public Environment parseEnviorment(Scanner inFile) {
		Environment env = new DefaultEnvironment();
		while (inFile.hasNext()) {
			String current = inFile.next();
			if (isOpenTag(current)) {
				switch (parseTag(current)) {
				case Name:
					env.setName(inFile.next());
					break;
				case Objects:
					env = parseObjects(inFile, env);
					break;
				default:
					break;
				}
			}
		}
		System.out.println("Finished");

		return env;
	}

	private Environment parseObjects(Scanner inFile, Environment env) {
		String current;
		RenderableObject ro;

		do {
			current = inFile.next();
			if (isOpenTag(current)) {
				switch (parseTag(current)) {
				case Type:
					ro = generateObject(inFile.next(), inFile);
					env.add(ro);
					break;
				default:
					break;
				}
			}
		} while (inFile.hasNext()
				&& !(isCloseTag(current) && parseTag(current) == LegalTag.Objects));
		
		return env;
	}

	private RenderableObject generateObject(String next, Scanner inFile) {
		RenderableObject ro = null;
		switch (next) {
		case "Ellipse":
			ro = generateElipse(inFile);
			break;
		case "Rectangle":
			ro = generateRectangle(inFile);
			break;
		case "Text":
			ro = generateText(inFile);
			break;
		case "Shape":
			ro = generateShape(inFile);
			break;
		}

		return ro;
	}

	private RenderableObject generateText(Scanner inFile) {
		Point loc = null;
		String t = "";
		Font f = null;
		Color c = null;
		String current = inFile.next();
		while (inFile.hasNext()
				&& !(isCloseTag(current) && parseTag(current) == LegalTag.Object)) {
			if (isOpenTag(current)) {
				switch (parseTag(current)) {
				case Location:
					loc = new Point(inFile.nextInt(), inFile.nextInt());
					break;
				case Text:
					Pattern p = inFile.delimiter();
					inFile.useDelimiter("</Text>");
					t = inFile.next();
					inFile.useDelimiter(p);
					break;
				case Font:
					int size,
					style = 0;
					inFile.next();
					String name = inFile.nextLine();
					style = inFile.nextInt();
					size = inFile.nextInt();
					f = new Font(name, style, size);

					break;
				case Color:
					c = new Color(inFile.nextInt(), inFile.nextInt(),
							inFile.nextInt());
					break;
				default:
					break;
				}
			}
			current = inFile.next();

		}
		Text text = new Text(t, f, c);

		text.setLocation(loc);

		return text;
	}

	private RenderableObject generateShape(Scanner inFile) {
		Point loc = null;
		RenderableShape rs = new RenderableShape(loc);
		String current = inFile.next();
		while (inFile.hasNext()
				&& !(isCloseTag(current) && parseTag(current) == LegalTag.Object)) {
			if (isOpenTag(current)) {
				switch (parseTag(current)) {
				case Location:
					loc = new Point(inFile.nextInt(), inFile.nextInt());
					rs.setLocation(loc);
					break;
				case Line:
					rs.add(generateLine(inFile));
					break;
				default:
					break;
				}
			}
			current = inFile.next();
		}
		return rs;
	}

	private Line generateLine(Scanner inFile) {
		Point s = null, e = null;
		int thickness = 0;
		Color c = null;
		String current = inFile.next();
		while (inFile.hasNext()
				&& !(isCloseTag(current) && parseTag(current) == LegalTag.Line)) {
			if (isOpenTag(current)) {
				switch (parseTag(current)) {
				case Start:
					s = new Point(inFile.nextInt(), inFile.nextInt());
					break;
				case End:
					e = new Point(inFile.nextInt(), inFile.nextInt());
					break;
				case Color:
					c = new Color(inFile.nextInt(), inFile.nextInt(),
							inFile.nextInt());
					break;
				case Thickness:
					thickness = inFile.nextInt();
					break;
				default:
					break;
				}
			}
			current = inFile.next();

		}

		Line l = new Line(s, e, thickness, c);
		return l;
	}

	private RenderableObject generateElipse(Scanner inFile) {
		Point s = null, e = null, loc = null;
		int thickness = 0;
		Color c = null;
		String current = inFile.next();
		while (inFile.hasNext()
				&& !(isCloseTag(current) && parseTag(current) == LegalTag.Object)) {
			if (isOpenTag(current)) {
				switch (parseTag(current)) {
				case Start:
					s = new Point(inFile.nextInt(), inFile.nextInt());
					break;
				case End:
					e = new Point(inFile.nextInt(), inFile.nextInt());
					break;
				case Color:
					c = new Color(inFile.nextInt(), inFile.nextInt(),
							inFile.nextInt());
					break;
				case Location:
					loc = new Point(inFile.nextInt(), inFile.nextInt());
					break;
				case Thickness:
					thickness = inFile.nextInt();
					break;
				default:
					break;
				}
			}

			current = inFile.next();
		}

		Ellipse ell = new Ellipse(s, e, thickness, c);
		ell.setLocation(loc);

		return ell;
	}

	private RenderableObject generateRectangle(Scanner inFile) {
		Point s = null, e = null, loc = null;
		int thickness = 0;
		Color c = null;
		String current = inFile.next();
		while (inFile.hasNext()
				&& !(isCloseTag(current) && parseTag(current) == LegalTag.Object)) {
			if (isOpenTag(current)) {
				switch (parseTag(current)) {
				case Start:
					s = new Point(inFile.nextInt(), inFile.nextInt());
					break;
				case End:
					e = new Point(inFile.nextInt(), inFile.nextInt());
					break;
				case Color:
					c = new Color(inFile.nextInt(), inFile.nextInt(),
							inFile.nextInt());
					break;
				case Location:
					loc = new Point(inFile.nextInt(), inFile.nextInt());
					break;
				case Thickness:
					thickness = inFile.nextInt();
					break;
				default:
					break;
				}
			}
			current = inFile.next();
		}

		Rectangle rect = new Rectangle(s, e, thickness, c);
		rect.setLocation(loc);

		return rect;
	}

}
