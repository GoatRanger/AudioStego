/*
 * Created on Sep 26, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package eecs;

/**
 * @author DK8685
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FileResults {
	
	StringBuffer messages;
	int stylesheets = 0;
	int bodyCount = 0;
	int headCount = 0;
	int htmlCount = 0;
	int olCount = 0;
	int ulCount = 0;
	int tableCount = 0;
	int headingCount = 0;
	int imgCount = 0;
	int hrCount = 0;
	int linkCount = 0;
	int internalLinkCount = 0;
	int styleColorCount = 0;
	int styleBackgroundCount = 0;
	int styleHrCount = 0;
	
	public FileResults() {
		messages = new StringBuffer();
	}
	
	public FileResults(FileResults other) {
		stylesheets = other.stylesheets;
		bodyCount = other.bodyCount;
		headCount = other.headCount;
		htmlCount = other.htmlCount;
		olCount = other.olCount;
		ulCount = other.ulCount;
		tableCount = other.tableCount;
		headingCount = other.headingCount;
		imgCount = other.imgCount;
		hrCount = other.hrCount;
		linkCount = other.linkCount;
		internalLinkCount = other.internalLinkCount;
    styleColorCount = other.styleColorCount;
    styleBackgroundCount = other.styleBackgroundCount;
    styleHrCount = other.styleHrCount;
		messages = new StringBuffer(other.messages.toString());	
	}
	
  public FileResults addResults(FileResults other) {
    stylesheets += other.stylesheets;
    bodyCount += other.bodyCount;
    headCount += other.headCount;
    htmlCount += other.htmlCount;
    olCount += other.olCount;
    ulCount += other.ulCount;
    tableCount += other.tableCount;
    headingCount += other.headingCount;
    imgCount += other.imgCount;
    hrCount += other.hrCount;
    linkCount += other.linkCount;
    internalLinkCount += other.internalLinkCount;
    styleColorCount += other.styleColorCount;
    styleBackgroundCount += other.styleBackgroundCount;
    styleHrCount += other.styleHrCount;
    messages.append(other.messages);
    return this;
  }
	public void setMessages(StringBuffer messages) {
		this.messages = messages;
	}

    /**
     * @return
     */
    public int getBodyCount() {
        return bodyCount;
    }

    /**
     * @param bodyCount
     */
    public void setBodyCount(int bodyCount) {
        this.bodyCount = bodyCount;
    }
    
    public void addBodyCount(int bodyCount) {
    	this.bodyCount += bodyCount;
    }

    /**
     * @return
     */
    public int getHeadCount() {
        return headCount;
    }

    /**
     * @param headCount
     */
    public void setHeadCount(int headCount) {
        this.headCount = headCount;
    }
    
	public void addHeadCount(int headCount) {
		this.headCount += headCount;
	}
    /**
     * @return
     */
    public int getHeadingCount() {
        return headingCount;
    }

    /**
     * @param headingCount
     */
    public void setHeadingCount(int headingCount) {
        this.headingCount = headingCount;
    }
    
	public void addHeadingCount(int headingCount) {
		this.headingCount += headingCount;
	}
    /**
     * @return
     */
    public int getHrCount() {
        return hrCount;
    }

    /**
     * @param hrCount
     */
    public void setHrCount(int hrCount) {
        this.hrCount = hrCount;
    }
    
	public void addHrCount(int hrCount) {
		this.hrCount += hrCount;
	}
    /**
     * @return
     */
    public int getHtmlCount() {
        return htmlCount;
    }

    /**
     * @param htmlCount
     */
    public void setHtmlCount(int htmlCount) {
        this.htmlCount = htmlCount;
    }
    
	public void addHtmlCount(int htmlCount) {
		this.htmlCount += htmlCount;
	}
    /**
     * @return
     */
    public int getImgCount() {
        return imgCount;
    }

    /**
     * @param imgCount
     */
    public void setImgCount(int imgCount) {
        this.imgCount = imgCount;
    }
    
	public void addImgCount(int imgCount) {
		this.imgCount += imgCount;
	}
    /**
     * @return
     */
    public int getLinkCount() {
        return linkCount;
    }

    /**
     * @param linkCount
     */
    public void setLinkCount(int linkCount) {
        this.linkCount = linkCount;
    }
    
	public void addLinkCount(int linkCount) {
		this.linkCount += linkCount;
	}
	
	public int getInternalLinkCount() {
		return internalLinkCount;
	}

	/**
	 * @param linkCount
	 */
	public void setInternalLinkCount(int linkCount) {
		this.internalLinkCount = linkCount;
	}
    
	public void addInternalLinkCount(int linkCount) {
		this.internalLinkCount += linkCount;
	}
	
    /**
     * @return
     */
    public int getOlCount() {
        return olCount;
    }

    /**
     * @param olCount
     */
    public void setOlCount(int olCount) {
        this.olCount = olCount;
    }
    
	public void addOlCount(int olCount) {
		this.olCount += olCount;
	}
    /**
     * @return
     */
    public int getStylesheets() {
        return stylesheets;
    }

    /**
     * @param stylesheets
     */
    public void setStylesheets(int stylesheets) {
        this.stylesheets = stylesheets;
    }
    
	public void addStyleCount(int styleCount) {
		this.stylesheets += styleCount;
	}
    /**
     * @return
     */
    public int getTableCount() {
        return tableCount;
    }

    /**
     * @param tableCount
     */
    public void setTableCount(int tableCount) {
        this.tableCount = tableCount;
    }
    
	public void addTableCount(int tableCount) {
		this.tableCount += tableCount;
	}
    /**
     * @return
     */
    public int getUlCount() {
        return ulCount;
    }

    /**
     * @param ulCount
     */
    public void setUlCount(int ulCount) {
        this.ulCount = ulCount;
    }
    
	public void addUlCount(int ulCount) {
		this.ulCount += ulCount;
	}
	
    /**
     * @return
     */
    public StringBuffer getMessages() {
        return messages;
    }
    
    public void appendMessage(String message) {
    	messages.append(message);
    }
    
    public void setStyleHrCount(int count) {
      styleHrCount = count;
    }
    
    public int getStyleHrCount() {
      return styleHrCount;
    }
    
    public void addStyleHrCount(int count) {
      styleHrCount += count;
    }
    
    
  public void setStyleColorCount(int count) {
    styleColorCount = count;
  }
    
  public int getStyleColorCount() {
    return styleColorCount;
  }
    
  public void addStyleColorCount(int count) {
    styleColorCount += count;
  }
    
  public void setStyleBackgroundCount(int count) {
    styleBackgroundCount = count;
  }
    
  public int getStyleBackgroundCount() {
    return styleBackgroundCount;
  }
    
  public void addStyleBackgroundCount(int count) {
    styleBackgroundCount += count;
  }

}
