import * as React from 'react';
import logoImg from '../../assets/WEATHERMENT.svg'

export default class Homepage extends React.Component {

  constructor() {
    super();
    this.state = {
      logo: { class: ['animated', 'delay-1s', 'fadeInDown', 'slow' ], animationEnd: undefined },
      bar:  { class: ['animated', 'delay-2s', 'fadeInUp', 'bottom' ], animationEnd: undefined }
    }
    this.counterAnimationEnd = 0;
  }

  async onSearch(event) {
    event.preventDefault();
    const animationEnd = this.onAnimationsEnd.bind(this);

    await this.setState({
      logo: { class: ['slow'] },
      bar:  { class: ['slow'] }
    });
    await this.setState({
      logo: { class: ['animated', 'fadeOutUp',   'delay-1s'], animationEnd },
      bar:  { class: ['animated', 'fadeOutDown', 'delay-1s'], animationEnd }
    });
  }

  onAnimationsEnd() {
    if(++this.counterAnimationEnd < 2) return;
    this.props.onSubmit();
  }

  render() {
    const { logo, bar } = this.state;

    return (
      <div className="splash">
        <img onAnimationEnd={logo.animationEnd} className={logo.class.join(' ')} src={logoImg} />
        <div onAnimationEnd={bar.animationEnd}  className={bar.class.join(' ')}>
          <form onSubmit={this.onSearch.bind(this)}>
            <input type="search" placeholder="Ricerca località" />
            <i className="fa fa-search"></i>
          </form>
        </div>
      </div>
    )
  }
}