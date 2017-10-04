 import javafx.ui.*;
 
        class Temp {
            attribute celsius: Number;
            attribute farenheit: Number;
            attribute showCelsius: Boolean;
            attribute showFarenheit: Boolean;
        }


        trigger on Temp.celsius = value {
            farenheit = (9/5 * celsius + 32);
        }

        trigger on Temp.farenheit = value {
            celsius = ((farenheit - 32) * 5/9);
        }


        Frame {

            var temp = Temp {
                farenheit: 32
                showFarenheit: true
                showCelsius: true
            }

            height: 300

            width: 400


            title: "Temperature"

            content: Box {
                orientation: VERTICAL
                content:
                [FlowPanel {
                    content:
                    [CheckBox {
                        text: "Show Celsius"
                        selected: bind temp.showCelsius
                    },
                    RigidArea {
                        width: 20
                    },
                    CheckBox {
                        text: "Show Farenheit"
                        selected: bind temp.showFarenheit
                    }]
                },
                Rect {        
            x: 70
            y: 205
            height: 80
            width: 470
            arcHeight: 20
            arcWidth: 20
            fill: color
            stroke: color
            strokeWidth: 2
            opacity: 0.9
        },
                Slider {
                    visible: bind temp.showCelsius
                    min: -100
                    max: 100
                    border: TitledBorder {title: "Celsius"}
                    value: bind temp.celsius
                    minorTickSpacing: 5
                    majorTickSpacing: 10
                    paintTicks: true
                    paintLabels: true
                    labels:
                    [SliderLabel {
                         value: 0
                         label: SimpleLabel {
                             text: "0"
                         }
                    },
                    SliderLabel {
                         value: 100
                         label: SimpleLabel {
                             text: "100"
                         }
                    }]
                },
                Slider {
                    visible: bind temp.showFarenheit
                    border: TitledBorder {title: "Farenheit"}
                    min: -148
                    max: 212
                    paintTicks: true
                    minorTickSpacing: 5
                    majorTickSpacing: 10
                    value: bind temp.farenheit
                    paintLabels: true
                    labels:
                    [SliderLabel {
                         value: 0
                         label: SimpleLabel {
                            text: "0"
                         }
                    },
                    SliderLabel {
                         value: 32
                         label: SimpleLabel {
                             text: "32"
                         }
                    },
                    SliderLabel {
                         value: 212
                         label: SimpleLabel {
                             text: "212"
                         }
                    }]
                },
                FlowPanel {
                     alignment: LEADING
                     content:
                     [SimpleLabel {
                          visible: bind temp.showCelsius
                          alignmentX: 1
                          text: "Celsius:"
                      },
                      Spinner {
                          visible: bind temp.showCelsius
                          min: -100
                          max: 100
                          value: bind temp.celsius
                      },
                      RigidArea {
                          width: 20
                      },
                      SimpleLabel {
                          visible: bind temp.showFarenheit
                          alignmentX: 1
                          text: "Farenheit:"
                      },
                      Spinner {
                          visible: bind temp.showFarenheit
                          min: -148
                          max: 212
                          value: bind temp.farenheit
                      }]
                 }]
            }

            visible: true

        }

