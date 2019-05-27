import * as React from 'react';
import Homepage from './Homepage';
import Graph from './Graph';

export default class App extends React.Component {

  constructor() {
    super();
    this.pages = Object.freeze({ homepage: 1, graph: 2 })
    this.state = {
      page: this.pages.homepage
    }
  }

  changePage(page) {
    this.setState({ page })
  }

  onHomepageSubmit() {
    this.changePage(this.pages.graph);
  }

  render() {
    switch(this.state.page) {
      case this.pages.graph:
        return <Graph />;
      case this.pages.homepage:
        return <Homepage onSubmit={this.onHomepageSubmit.bind(this)} />
    }
  }
}