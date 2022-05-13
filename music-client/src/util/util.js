export default {
  // 数字过万的处理
  formatNum (val) {
    let num = 0;
    if (val > 9999) {
      num = Math.round(val / 10000 * 10) / 10 + '万';
    } else {
      num = val;
    }
    return num;
  },
  //将时间戳格式化为 xxxx-xx-xx
  dateFormat(time){
    let date = new Date(time);
    let year = date.getFullYear();
    let month = date.getMonth()+1;
    let day = date.getDate();
    return year + "-" + month + "-" + day;
  },
  // 时间戳格式化为 2020-10-30 09:30:00
  dateFormatLong(time){
    let date = new Date(time);
    let year = date.getFullYear();
    let month = date.getMonth()+1;
    let day = date.getDate();
    let hour = date.getHours();
    let min = date.getMinutes();
    let second = date.getSeconds();
    return year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + second;
  },
  // 歌曲转毫秒格式化处理 03:30 => (3*60+30) * 1000
  formatSongSecond (duration) {
    let arr = duration.split(':'), second = 0;
    for(let i = 0; i < arr.length; i++) {
      second += arr[i] * 60 * (arr.length - 1 - i);
    }
    second += arr[arr.length - 1] * 1;
    return second;
  },
  // 歌曲毫秒格式化处理 03:30
  formatSongTime (duration) {
    duration = duration >= 0 ? duration / 1000 : 0;
    const m = (Math.floor(duration / 60) + '').padStart(2, '0');
    const s = (Math.floor(duration % 60) + '').padStart(2, '0');
    return `${m}:${s}`;
  },
  // 评论时间格式化处理
  formatMsgTime (duration) {
    let result = ''
    const NOW = new Date()
    const PAST = new Date(duration)

    // 判断是当天的时间 显示格式 10：30
    if (NOW.toDateString() === PAST.toDateString()) {
      result = this.formartDate(duration, 'HH:mm')
      // 时间为当年 显示月日 时间戳
    } else if (PAST.getFullYear() === NOW.getFullYear()) {
      result = this.formartDate(duration, 'MM月dd日 HH:mm')
    } else {
      result = this.formartDate(duration, 'yyyy年MM月dd日')
    }
    return result
  },
  // 添加歌曲到播放列表，过滤重复的歌曲
  concatPlayList (newList = [], oldList = []) {
    const arr = [...oldList, ...newList]
    const map = new Map()
    for (const item of arr) {
      if (!map.has(item.id)) {
        map.set(item.id, item)
      }
    }
    return [...map.values()]
  },
  // 处理歌曲
  formatSongs (list, privileges) {
    const ret = []
    list.map((item, index) => {
      if (item.id) {
        // 是否有版权播放
        item.license = !privileges[index].cp
        ret.push(formatSongInfo(item))
      }
    })
    return ret
  }
}