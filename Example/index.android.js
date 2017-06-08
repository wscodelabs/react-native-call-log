
import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  FlatList
} from 'react-native';
import CallLog from 'react-native-call-log';

export default class calllog extends Component {
  constructor(props) {
    super(props);
    this.state= {
      calllog: null
    }
  }

  renderCall(item,index) {
    return(
      <View style={{flex: 1}}>
        <View style={styles.phNumContainer}>
          <View style={styles.Heading}>
            <Text>Phone Number</Text>
          </View>
          <View style={styles.content} >
            <Text>{item.phoneNumber}</Text>
          </View>
        </View>
        <View style={styles.phNumContainer}>
          <View style={styles.Heading}>
            <Text>Call Type</Text>
          </View>
          <View style={styles.content} >
            <Text>{item.callType}</Text>
          </View>
        </View>
          
        </View>
        
    );
  }
  componentDidMount() {
    CallLog.show((status)=> {
      console.log(JSON.parse(status))
      this.setState({calllog: JSON.parse(status)}) }
    )
  }
  render() {
    console.log(this.state.calllog);
    if(!this.state.calllog){
      return(
        <View style={styles.container}>
          <Text>Loading Call logs ....</Text>
        </View>
      )
    }

    return (
      <View style={styles.container}>
        <View style={{flex: 1}}>
          <Text style={{textAlign: 'center',fontSize: 24}}>Call Details</Text>
        </View>
        <View style={{flex: 10}}>
          <FlatList 
            data= {this.state.calllog}
            renderItem= {({item,index})=> this.renderCall(item,index)}
            ItemSeparatorComponent= {()=> <View><Text>---------------------------------------------------</Text></View> }
            keyExtractor= {(item,index)=> index}
          />
        </View>
        
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F5FCFF',
  },
  phNumContainer: {
    flex: 1,
    flexDirection: 'row'
  },
  Heading: {
    flex: 3,
  },
  content: {
    flex: 6
  }

});

AppRegistry.registerComponent('calllog', () => calllog);
