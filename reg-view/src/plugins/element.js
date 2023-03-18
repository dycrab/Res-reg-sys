import Vue from 'vue'
import { Button } from 'element-ui'
import { Form, FormItem } from 'element-ui'
import { Input } from 'element-ui'
import { Message, MessageBox } from 'element-ui'
import { Upload } from 'element-ui'
import { Menu, MenuItem, MenuItemGroup, Container, Header, Aside, Main, Submenu, Footer } from 'element-ui'
import { Row, Col } from 'element-ui'
import { Pagination, Card } from 'element-ui'
import { Carousel, CarouselItem } from 'element-ui'
import { Divider } from 'element-ui'
import { Tabs, TabPane } from 'element-ui'
import { Table, TableColumn } from 'element-ui'
import { Image } from 'element-ui'
import { Tag } from 'element-ui'
import { Empty } from 'element-ui'
import { Loading } from 'element-ui';
import { Select } from 'element-ui'
import { Option } from 'element-ui'
import { Dialog } from 'element-ui'
import { Checkbox, CheckboxGroup,TimeSelect } from 'element-ui'
import { Backtop, OptionGroup } from 'element-ui'
import { Icon, Popover } from 'element-ui'
import { Breadcrumb, BreadcrumbItem, Descriptions } from 'element-ui'
import { DatePicker, Switch, RadioGroup, Radio, Result, DescriptionsItem } from 'element-ui'
Vue.prototype.$message = Message
Vue.prototype.$confirm = MessageBox.confirm
Vue.prototype.$alert = MessageBox.alert
Vue.prototype.$msgbox = MessageBox
Vue.use(TimeSelect)
Vue.use(OptionGroup)
Vue.use(Descriptions)
Vue.use(DescriptionsItem)
Vue.use(MenuItemGroup)
Vue.use(Button)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)
Vue.use(Upload)
Vue.use(Menu)
Vue.use(MenuItem)
Vue.use(Container)
Vue.use(Header)
Vue.use(Aside)
Vue.use(Main)
Vue.use(Submenu)
Vue.use(Row)
Vue.use(Col)
Vue.use(Pagination)
Vue.use(Card)
Vue.use(Carousel)
Vue.use(CarouselItem)
Vue.use(Divider)
Vue.use(Tabs)
Vue.use(TabPane)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Image)
Vue.use(Tag)
Vue.use(Empty)
Vue.use(Result)
Vue.use(Loading.directive);
Vue.use(Select)
Vue.use(Option)
Vue.use(Dialog)
Vue.use(Checkbox)
Vue.use(CheckboxGroup)
Vue.use(Footer)
Vue.use(Backtop)
Vue.use(Icon)
Vue.use(Popover)
Vue.use(Breadcrumb)
Vue.use(BreadcrumbItem)
Vue.use(DatePicker)
Vue.use(Switch)
Vue.use(Radio)
Vue.use(RadioGroup)