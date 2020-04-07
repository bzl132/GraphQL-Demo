## Schema 和类型

### 类型系统（Type System）

```
{ // root
  hero { // hero对象
    name // name字段
    appearsIn //字段
  }
}
```

#### 对象类型和字段（Object Types and Fields）

```
type Character {
  name: String!
  appearsIn: [Episode!]!
}
```

- Character:  对象类型

- name appearsIn: 字段

- String ： 标量类型

- !：非空

- [Episode!]! 表示一个 `Episode` **数组**。因为它也是**非空的**

#### 参数（Arguments）

```
type Starship {
  id: ID!
  name: String!
  length(unit: LengthUnit = METER): Float
}
```

unit : 参数名

LengthUnit ：参数类型

METER ：默认值

#### 查询和变更类型（The Query and Mutation Types）

```
schema {
  query: Query
  mutation: Mutation
}
```

```
type Query {
  hero(episode: Episode): Character
  droid(id: ID!): Droid
}
```

#### 标量类型（Scalar Types）

GraphQL 自带一组默认标量类型：

- `Int`：有符号 32 位整数。
- `Float`：有符号双精度浮点值。
- `String`：UTF‐8 字符序列。
- `Boolean`：`true` 或者 `false`。
- `ID`：ID 标量类型表示一个唯一标识符，通常用以重新获取对象或者作为缓存中的键。ID 类型使用和 String 一样的方式序列化；然而将其定义为 ID 意味着并不需要人类可读型。

自定义标量类型：

```
scalar Date
```

#### 枚举类型（Enumeration Types）

1. 验证这个类型的任何参数是可选值的的某一个
2. 与类型系统沟通，一个字段总是一个有限值集合的其中一个值。

```
enum Episode {
  NEWHOPE
  EMPIRE
  JEDI
}
```

#### 列表和非空（Lists and Non-Null）

```
type Character {
  name: String!
  appearsIn: [Episode]!
}
```

! : 非空

[] : 列表List

#### 接口（Interfaces）

**接口**是一个抽象类型，它包含某些字段，而对象类型必须包含这些字段，才能算实现了这个接口。

```
interface Character {
  id: ID!
  name: String!
  friends: [Character]
  appearsIn: [Episode]!
}
```

```
type Human implements Character {
  id: ID!
  name: String!
  friends: [Character]
  appearsIn: [Episode]!
  starships: [Starship]
  totalCredits: Int
}

type Droid implements Character {
  id: ID!
  name: String!
  friends: [Character]
  appearsIn: [Episode]!
  primaryFunction: String
}
```

#### 联合类型（Union Types）

```
union SearchResult = Human | Droid | Starship
```

```
{
  search(text: "an") {
    __typename
    ... on Human {
      name
      height
    }
    ... on Droid {
      name
      primaryFunction
    }
    ... on Starship {
      name
      length
    }
  }
}

{
  "data": {
    "search": [
      {
        "__typename": "Human",
        "name": "Han Solo",
        "height": 1.8
      },
      {
        "__typename": "Human",
        "name": "Leia Organa",
        "height": 1.5
      },
      {
        "__typename": "Starship",
        "name": "TIE Advanced x1",
        "length": 9.2
      }
    ]
  }
}
```

`_typename` 字段解析为 `String`，它允许你在客户端区分不同的数据类型。

```
{
  search(text: "an") {
    __typename
    ... on Character {
      name
    }
    ... on Human {
      height
    }
    ... on Droid {
      primaryFunction
    }
    ... on Starship {
      name
      length
    }
  }
}
```

#### 输入类型（Input Types）

枚举/标量/对象 作为入参

```
input ReviewInput {
  stars: Int!
  commentary: String
}
```

```
mutation CreateReviewForEpisode($ep: Episode!, $review: ReviewInput!) {
  createReview(episode: $ep, review: $review) {
    stars
    commentary
  }
}

{
  "ep": "JEDI",
  "review": {
    "stars": 5,
    "commentary": "This is a great movie!"
  }
}

{
  "data": {
    "createReview": {
      "stars": 5,
      "commentary": "This is a great movie!"
    }
  }
}
```

